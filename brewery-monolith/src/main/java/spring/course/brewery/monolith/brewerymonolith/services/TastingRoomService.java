package spring.course.brewery.monolith.brewerymonolith.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.course.brewery.monolith.brewerymonolith.bootstrap.DefaultBreweryLoader;
import spring.course.brewery.monolith.brewerymonolith.domain.Beer;
import spring.course.brewery.monolith.brewerymonolith.domain.Customer;
import spring.course.brewery.monolith.brewerymonolith.repositories.BeerOrderRepository;
import spring.course.brewery.monolith.brewerymonolith.repositories.BeerRepository;
import spring.course.brewery.monolith.brewerymonolith.repositories.CustomerRepository;
import spring.course.brewery.monolith.brewerymonolith.web.model.BeerOrderDto;
import spring.course.brewery.monolith.brewerymonolith.web.model.BeerOrderLineDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
@Slf4j
public class TastingRoomService {

    private final CustomerRepository customerRepository;
    private final BeerOrderService beerOrderService;

    private final BeerRepository beerRepository;
    private final BeerOrderRepository beerOrderRepository;

    public TastingRoomService(CustomerRepository customerRepository, BeerOrderService beerOrderService, BeerRepository beerRepository,
                              BeerOrderRepository beerOrderRepository) {
        this.customerRepository = customerRepository;
        this.beerOrderService = beerOrderService;
        this.beerRepository = beerRepository;
        this.beerOrderRepository = beerOrderRepository;
    }

    @Transactional
    @Scheduled(fixedRate = 2000) //run every 2 seconds
    public void placeTastingRoomOrder(){

        List<Customer> customerList = customerRepository.findAllByCustomerNameLike(DefaultBreweryLoader.TASTING_ROOM);

        if (customerList.size() == 1){ //should be just one
            doPlaceOrder(customerList.get(0));
        } else {
            log.error("Too many or too few tasting room customers found");
        }
    }

    private void doPlaceOrder(Customer customer) {
        Beer beerToOrder = getRandomBeer();

        BeerOrderLineDto beerOrderLine = BeerOrderLineDto.builder()
                .beerId(beerToOrder.getId())
                .orderQuantity(new Random().nextInt(6)) //todo externalize value to property
                .build();

        List<BeerOrderLineDto> beerOrderLineSet = new ArrayList<>();
        beerOrderLineSet.add(beerOrderLine);

        BeerOrderDto beerOrder = BeerOrderDto.builder()
                .customerId(customer.getId())
                .customerRef(UUID.randomUUID().toString())
               // .orderStatusCallbackUrl("http://localhost:8080/beerorder") //todo update
                .beerOrderLines(beerOrderLineSet)
                .build();

        BeerOrderDto savedOrder = beerOrderService.placeOrder(customer.getId(), beerOrder);

        log.debug("Saved Tasting Room Order: " + savedOrder.getId());
    }

    private Beer getRandomBeer() {
        List<Beer> beers = beerRepository.findAll();

        return beers.get(new Random().nextInt(beers.size() -0));
    }
}
