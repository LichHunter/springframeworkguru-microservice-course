package spring.course.mssc.brewery.web.controller;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import spring.course.mssc.brewery.web.model.CustomerDto;
import spring.course.mssc.brewery.web.service.CustomerService;

@Validated
@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDto> getCustomer(@NotNull @PathVariable("customerId") UUID id) {
        CustomerDto customer = customerService.getById(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity handlePost(@Valid @RequestBody CustomerDto dto) {
        CustomerDto savedCustomer = customerService.save(dto);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/customer" + savedCustomer.getId().toString());

        return new ResponseEntity(headers, CREATED);
    }

    @PutMapping("/{customerId}")
    @ResponseStatus(NO_CONTENT)
    public void handleUpdate(@PathVariable("customerId") UUID id, @Valid @RequestBody CustomerDto dto) {
        customerService.update(id, dto);
    }

    @DeleteMapping("/{customerId}")
    @ResponseStatus(NO_CONTENT)
    public void deleteCustomer(@PathVariable("customerId") UUID id) {
        customerService.deleteById(id);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List<String>> validationErrorHandler(ConstraintViolationException e) {
        return new ResponseEntity<>(e.getConstraintViolations().stream()
            .map(error -> error.getPropertyPath() + " : " + error.getMessage())
            .collect(Collectors.toList()), BAD_REQUEST);
    }
}
