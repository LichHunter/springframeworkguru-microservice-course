package spring.course.brewery.monolith.brewerymonolith.web.mappers;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import spring.course.brewery.monolith.brewerymonolith.domain.BeerOrderLine;
import spring.course.brewery.monolith.brewerymonolith.web.model.BeerOrderLineDto;

@Mapper(uses = {DateMapper.class}, componentModel = "spring")
@DecoratedWith(BeerOrderLineMapperDecorator.class)
public interface BeerOrderLineMapper {
    BeerOrderLineDto beerOrderLineToDto(BeerOrderLine line);

    BeerOrderLine dtoToBeerOrderLine(BeerOrderLineDto dto);
}
