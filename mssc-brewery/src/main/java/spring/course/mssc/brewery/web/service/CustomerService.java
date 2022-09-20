package spring.course.mssc.brewery.web.service;

import java.util.UUID;

import spring.course.mssc.brewery.web.model.CustomerDto;

public interface CustomerService {
    CustomerDto getById(UUID id);

    CustomerDto save(CustomerDto dto);

    void update(UUID id, CustomerDto dto);

    void deleteById(UUID id);
}
