package spring.course.mssc.beer.order.service.web.mappers;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import spring.course.mssc.beer.order.service.domain.BeerOrderLine;
import spring.course.mssc.brewery.model.BeerOrderLineDto;

@Mapper(uses = {DateMapper.class}, componentModel = "spring")
@DecoratedWith(BeerOrderLineMapperDecorator.class)
public interface BeerOrderLineMapper {
    BeerOrderLineDto beerOrderLineToDto(BeerOrderLine line);

    BeerOrderLine dtoToBeerOrderLine(BeerOrderLineDto dto);
}
