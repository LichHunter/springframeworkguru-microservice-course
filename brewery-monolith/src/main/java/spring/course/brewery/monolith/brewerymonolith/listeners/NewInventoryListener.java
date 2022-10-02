package spring.course.brewery.monolith.brewerymonolith.listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import spring.course.brewery.monolith.brewerymonolith.domain.BeerOrder;
import spring.course.brewery.monolith.brewerymonolith.domain.OrderStatusEnum;
import spring.course.brewery.monolith.brewerymonolith.events.NewBeerOrderEvent;
import spring.course.brewery.monolith.brewerymonolith.events.NewInventoryEvent;
import spring.course.brewery.monolith.brewerymonolith.repositories.BeerOrderRepository;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Component
public class NewInventoryListener {

    private final BeerOrderRepository beerOrderRepository;
    private final ApplicationEventPublisher publisher;

    public NewInventoryListener(BeerOrderRepository beerOrderRepository,
                                ApplicationEventPublisher publisher) {
        this.beerOrderRepository = beerOrderRepository;
        this.publisher = publisher;
    }

    @Async
    @EventListener
    @Transactional
    public void listen(NewInventoryEvent event){
        List<BeerOrder> newOrders = beerOrderRepository.findAllByOrderStatus(OrderStatusEnum.NEW);
        List<BeerOrder> readyOrders = beerOrderRepository.findAllByOrderStatus(OrderStatusEnum.READY);

        log.debug("NEW Orders: " + newOrders.size());
        log.debug("READY Orders: " + readyOrders.size());

        newOrders.forEach(beerOrder -> {
            log.debug("Republishing New Order Event for New Inventory. BeerOrder - " + beerOrder.getId());
            publisher.publishEvent(new NewBeerOrderEvent(beerOrder));
        });

    }

}
