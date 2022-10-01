package spring.course.mssc.beer.service.service;

import spring.course.mssc.beer.service.web.model.BeerDto;

import java.util.UUID;

public interface BeerService {
    BeerDto getById(UUID id);

    BeerDto save(BeerDto dto);

    BeerDto update(UUID id, BeerDto dto);
}
