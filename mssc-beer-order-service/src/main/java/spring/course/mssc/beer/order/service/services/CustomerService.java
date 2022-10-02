package spring.course.mssc.beer.order.service.services;

import org.springframework.data.domain.Pageable;
import spring.course.mssc.brewery.model.CustomerPagedList;

/**
 * Created by jt on 3/7/20.
 */
public interface CustomerService {

    CustomerPagedList listCustomers(Pageable pageable);

}
