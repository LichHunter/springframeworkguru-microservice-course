package spring.course.restdocs.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

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
    @Size(min = 3, max = 100)
    private String beerName;
    @NotNull
    private BeerStyle beerStyle;
    @Positive
    @NotNull
    private Long upc;
    @Positive
    @NotNull
    private BigDecimal price;
    @Positive
    private Integer quantityOnHand;
}
