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

    @Override
    public CustomerDto save(CustomerDto dto) {
        return CustomerDto.builder()
            .id(UUID.randomUUID())
            .name("Saved")
            .build();
    }

    @Override
    public void update(UUID id, CustomerDto dto) {
        //todo implement
    }

    @Override
    public void deleteById(UUID id) {
        //todo implement
    }
}
