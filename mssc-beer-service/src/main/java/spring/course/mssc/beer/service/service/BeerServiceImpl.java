package spring.course.mssc.beer.service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.course.mssc.beer.service.domain.Beer;
import spring.course.mssc.beer.service.repository.BeerRepository;
import spring.course.mssc.beer.service.web.controller.NotFoundException;
import spring.course.mssc.beer.service.web.mapper.BeerMapper;
import spring.course.mssc.beer.service.web.model.BeerDto;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {
    private final BeerRepository repository;
    private final BeerMapper mapper;

    @Override
    public BeerDto getById(UUID id) {
        Beer beer = repository.findById(id).orElseThrow(NotFoundException::new);
        return mapper.beerToBeerDto(beer);
    }

    @Override
    public BeerDto save(BeerDto dto) {
        Beer toSave = mapper.beerDtoToBeer(dto);
        Beer saved = repository.save(toSave);
        return mapper.beerToBeerDto(saved);
    }

    @Override
    public BeerDto update(UUID id, BeerDto dto) {
        Beer beer = repository.findById(id).orElseThrow(NotFoundException::new);

        beer.setBeerName(dto.getBeerName());
        beer.setBeerStyle(dto.getBeerStyle().name());
        beer.setPrice(dto.getPrice());
        beer.setUpc(dto.getUpc());

        return mapper.beerToBeerDto(repository.save(beer));
    }
}
