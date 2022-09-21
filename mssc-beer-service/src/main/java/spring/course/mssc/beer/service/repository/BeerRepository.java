package spring.course.mssc.beer.service.repository;

import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import spring.course.mssc.beer.service.domain.Beer;

@Repository
public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID> {
}
