package spring.course.mssc.beer.service.web.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BeerDto {
    private UUID id;
    private Integer version;
    private OffsetDateTime createDate;
    private OffsetDateTime lastModifiedDate;
    private String beerName;
    private BeerStyle beerStyle;
    private Long upc;
    private BigDecimal price;
    private Integer quantityOnHand;
}
