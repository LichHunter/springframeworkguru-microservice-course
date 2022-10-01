package spring.course.mssc.beer.service.bootstrap;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import spring.course.mssc.beer.service.domain.Beer;
import spring.course.mssc.beer.service.repository.BeerRepository;

import java.math.BigDecimal;

@Slf4j
@Component
@RequiredArgsConstructor
public class BeerLoader implements CommandLineRunner {
    public static final String BEER_UPC = "1231231231234";
    public static final String BEER_UPC_1 = "1231231231235";

    private final BeerRepository repository;

    @Override
    public void run(String... args) throws Exception {
        loadBeers();
    }

    private void loadBeers() {
        if (repository.count() == 0) {
            repository.save(Beer.builder()
                    .beerName("Mango Bobs")
                    .beerStyle("IPA")
                    .minOnHand(12)
                    .quantityToBrew(200)
                    .upc(BEER_UPC)
                .price(new BigDecimal("12.99"))
                .build());

            repository.save(Beer.builder()
                    .beerName("Galaxy Cat")
                    .beerStyle("Pale Ale")
                    .minOnHand(12)
                    .quantityToBrew(200)
                    .upc(BEER_UPC_1)
                .price(new BigDecimal("11.99"))
                .build());
        }

        log.info("Loaded beers: {}", repository.count());
    }
}
