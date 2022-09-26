package spring.course.mssc.brewery.web.mapper;

import org.mapstruct.Mapper;
import spring.course.mssc.brewery.domain.Beer;
import spring.course.mssc.brewery.web.model.BeerDto;

@Mapper(uses = {DateMapper.class})
public interface BeerMapper {

    BeerDto beerToBeerDto(Beer beer);

    Beer beerDtoToBeer(BeerDto dto);
}
