package spring.course.mssc.brewery.web.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import spring.course.mssc.brewery.web.model.CustomerDto;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Override
    public CustomerDto getById(UUID id) {
        return CustomerDto.builder()
            .id(UUID.randomUUID())
            .name("Alex")
            .build();
    }

}
