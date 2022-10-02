package spring.course.mssc.beer.order.service.web.mappers;

import org.mapstruct.Mapper;
import spring.course.mssc.beer.order.service.domain.Customer;
import spring.course.mssc.brewery.model.CustomerDto;

/**
 * Created by jt on 3/7/20.
 */
@Mapper(uses = {DateMapper.class}, componentModel = "spring")
public interface CustomerMapper {
    CustomerDto customerToDto(Customer customer);

    Customer dtoToCustomer(Customer dto);
}
