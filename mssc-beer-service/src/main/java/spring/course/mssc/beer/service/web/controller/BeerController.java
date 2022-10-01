package spring.course.mssc.beer.service.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import spring.course.mssc.beer.service.service.BeerService;
import spring.course.mssc.beer.service.web.model.BeerDto;

import java.util.UUID;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v1/beer")
@RequiredArgsConstructor
public class BeerController {
    private final BeerService service;

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable("beerId") UUID id) {
        return new ResponseEntity<>(service.getById(id), OK);
    }

    @PostMapping
    public ResponseEntity<BeerDto> save(@Validated @RequestBody BeerDto dto) {
        return new ResponseEntity<>(service.save(dto), CREATED);
    }

    @PutMapping("/{beerId}")
    @ResponseStatus(NO_CONTENT)
    public void update(@PathVariable("beerId") UUID id, @Validated @RequestBody BeerDto dto) {
        service.update(id, dto);
    }
}
