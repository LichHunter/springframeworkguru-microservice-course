package spring.course.restdocs.web.mapper;

import org.mapstruct.Mapper;
import spring.course.restdocs.domain.Beer;
import spring.course.restdocs.web.model.BeerDto;

@Mapper(uses = {DateMapper.class})
public interface BeerMapper {
    BeerDto beerToBeerDto(Beer beer);

    Beer beerDtoToBeer(BeerDto dto);
}
