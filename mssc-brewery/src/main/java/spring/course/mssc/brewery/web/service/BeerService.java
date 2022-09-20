package spring.course.mssc.brewery.web.service;

import java.util.UUID;

import spring.course.mssc.brewery.web.model.BeerDto;


public interface BeerService {
    BeerDto getBeerById(UUID beerId);
}
