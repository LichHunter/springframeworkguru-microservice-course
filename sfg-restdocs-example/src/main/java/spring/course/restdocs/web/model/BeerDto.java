package spring.course.restdocs.web.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
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
    @Null
    private UUID id;
    @Null
    private Integer version;
    @Null
    private OffsetDateTime createDate;
    @Null
    private OffsetDateTime lastModifiedDate;
    @NotBlank
    private String beerName;
    @NotNull
    private BeerStyle beerStyle;
    @Positive
    @NotNull
    private Long upc;
    @Positive
    @NotNull
    private BigDecimal price;
    private Integer quantityOnHand;
}