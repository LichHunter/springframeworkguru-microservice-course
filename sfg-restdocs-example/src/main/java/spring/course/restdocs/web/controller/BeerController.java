package spring.course.restdocs.web.controller;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

import java.util.UUID;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import spring.course.restdocs.repository.BeerRepository;
import spring.course.restdocs.web.mapper.BeerMapper;
import spring.course.restdocs.web.model.BeerDto;

@RestController
@FieldDefaults(makeFinal = true, level = PRIVATE)
@RequiredArgsConstructor
@RequestMapping("/api/v1/beer")
public class BeerController {
    BeerMapper mapper;
    BeerRepository repository;

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable("beerId") UUID id) {
        return new ResponseEntity<>(mapper.beerToBeerDto(repository.findById(id).get()), OK);
    }

    @PostMapping
    public ResponseEntity save(@Validated @RequestBody BeerDto dto) {
        repository.save(mapper.beerDtoToBeer(dto));
        return new ResponseEntity(CREATED);
    }

    @PutMapping("/{beerId}")
    @ResponseStatus(NO_CONTENT)
    public void update(@PathVariable("beerId") UUID id, @Validated @RequestBody BeerDto dto) {
        repository.findById(id)
            .ifPresent(beer -> {
                beer.setBeerName(dto.getBeerName());
                beer.setBeerStyle(dto.getBeerStyle().name());
                beer.setPrice(dto.getPrice());
                beer.setUpc(dto.getUpc());

                repository.save(beer);
            });
    }
}
