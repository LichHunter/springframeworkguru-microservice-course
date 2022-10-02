package spring.course.brewery.monolith.brewerymonolith.services;

import spring.course.brewery.monolith.brewerymonolith.web.model.BeerDto;

import java.util.Optional;
import java.util.UUID;

public interface BeerService {

    Optional<BeerDto> getBeerById(UUID uuid);
}
