package spring.course.mssc.brewery.web.service;

import java.util.UUID;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import spring.course.mssc.brewery.web.model.BeerDto;

@Slf4j
@Service
public class BeerServiceImpl implements BeerService {

    @Override
    public BeerDto getBeerById(UUID beerId) {
        return BeerDto.builder()
            .id(UUID.randomUUID())
            .beerName("Galaxy Cat")
            .beerStyle("Pale Ale")
            .build();
    }

    @Override
    public BeerDto saveNewBeer(BeerDto dto) {
        return BeerDto.builder()
            .id(UUID.randomUUID())
            .build();
    }

    @Override
    public void updateBeer(UUID id, BeerDto dto) {
        //todo add real impl to update beer
    }

    @Override
    public void deleteById(UUID id) {
        log.debug("Deleting beer...");
    }

}
