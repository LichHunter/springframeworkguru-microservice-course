package spring.course.mssc.beer.service.web.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

import java.util.UUID;

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
import spring.course.mssc.beer.service.web.model.BeerDto;

@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable("beerId") UUID id) {
        //todo impl
        return new ResponseEntity<>(BeerDto.builder().build(), OK);
    }

    @PostMapping
    public ResponseEntity save(@Validated @RequestBody BeerDto dto) {
        //todo iml
        return new ResponseEntity(CREATED);
    }

    @PutMapping("/{beerId}")
    @ResponseStatus(NO_CONTENT)
    public void update(@PathVariable("beerId") UUID id, @Validated @RequestBody BeerDto dto) {
        //todo iml
    }
}
