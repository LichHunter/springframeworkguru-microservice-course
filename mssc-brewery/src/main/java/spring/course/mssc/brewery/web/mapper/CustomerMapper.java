package spring.course.mssc.brewery.web.mapper;

import org.mapstruct.Mapper;
import spring.course.mssc.brewery.domain.Customer;
import spring.course.mssc.brewery.web.model.CustomerDto;

@Mapper
public interface CustomerMapper {

    CustomerDto customerToCustomerDto(Customer customer);

    Customer customerDtoToCustomer(CustomerDto dto);
}
