package spring.course.restdocs.web.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;
import spring.course.restdocs.domain.Beer;
import spring.course.restdocs.repository.BeerRepository;
import spring.course.restdocs.web.model.BeerDto;
import spring.course.restdocs.web.model.BeerStyle;

@ExtendWith(RestDocumentationExtension.class)
@AutoConfigureRestDocs
@WebMvcTest(BeerController.class)
@ComponentScan(basePackages = "spring.course.restdocs.web.mapper")
class BeerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private BeerRepository repository;

    @Test
    void getBeerById() throws Exception {
        UUID id = UUID.randomUUID();
        given(repository.findById(id)).willReturn(Optional.of(Beer.builder().build()));

        mockMvc.perform(
                get("/api/v1/beer/{beerId}", id.toString()).accept(APPLICATION_JSON)
            ).andExpect(status().isOk())
            .andDo(document("v1/beer",
                    pathParameters(
                        parameterWithName("beerId").description("UUID of desired beer to get.")
                    ),
                    responseFields(
                        fieldWithPath("id").description("Id of Beer"),
                        fieldWithPath("version").description("Version number"),
                        fieldWithPath("createDate").description("Date created"),
                        fieldWithPath("lastModifiedDate").description("Date updated"),
                        fieldWithPath("beerName").description("Beer name"),
                        fieldWithPath("beerStyle").description("Beer style"),
                        fieldWithPath("upc").description("UPC of beer"),
                        fieldWithPath("price").description("Price"),
                        fieldWithPath("quantityOnHand").description("Quantity on hand")
                    )
                )
            );
    }

    @Test
    void saveNewBeer() throws Exception {
        BeerDto dto = getValidBeerDto();
        String beerDtoJson = mapper.writeValueAsString(dto);

        mockMvc.perform(
                post("/api/v1/beer/")
                    .contentType(APPLICATION_JSON)
                    .content(beerDtoJson)
            ).andExpect(status().isCreated())
            .andDo(document("v1/beer",
                requestFields(
                    fieldWithPath("id").ignored(),
                    fieldWithPath("version").ignored(),
                    fieldWithPath("createDate").ignored(),
                    fieldWithPath("lastModifiedDate").ignored(),
                    fieldWithPath("beerName").description("Beer name"),
                    fieldWithPath("beerStyle").description("Beer style"),
                    fieldWithPath("upc").description("UPC of beer").attributes(),
                    fieldWithPath("price").description("Price"),
                    fieldWithPath("quantityOnHand").ignored()
                )));
    }

    @Test
    void updateBeerById() throws Exception {
        BeerDto dto = getValidBeerDto();
        String beerDtoJson = mapper.writeValueAsString(dto);

        mockMvc.perform(
            put("/api/v1/beer/" + UUID.randomUUID().toString())
                .contentType(APPLICATION_JSON)
                .content(beerDtoJson)
        ).andExpect(status().isNoContent());
    }

    private BeerDto getValidBeerDto() {
        return BeerDto.builder()
            .beerName("Nice Ale")
            .beerStyle(BeerStyle.ALE)
            .price(new BigDecimal("9.99"))
            .upc(1L)
            .build();
    }
}