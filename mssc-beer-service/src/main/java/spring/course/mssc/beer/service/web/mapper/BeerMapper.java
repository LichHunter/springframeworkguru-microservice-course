package spring.course.mssc.beer.service.web.mapper;

import org.mapstruct.Mapper;
import spring.course.mssc.beer.service.domain.Beer;
import spring.course.mssc.beer.service.web.model.BeerDto;

@Mapper(uses = {DateMapper.class})
public interface BeerMapper {
    BeerDto beerToBeerDto(Beer beer);

    Beer beerDtoToBeer(BeerDto dto);
}
