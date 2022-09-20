package spring.course.mssc.brewery.web.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import spring.course.mssc.brewery.web.model.CustomerDto;
import spring.course.mssc.brewery.web.service.CustomerService;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDto> getCutomer(@PathVariable("customerId") UUID id) {
        CustomerDto customer = customerService.getById(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

}
