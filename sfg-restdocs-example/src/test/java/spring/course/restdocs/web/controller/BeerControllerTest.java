package spring.course.restdocs.web.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.web.servlet.MockMvc;
import spring.course.restdocs.domain.Beer;
import spring.course.restdocs.repository.BeerRepository;
import spring.course.restdocs.web.model.BeerDto;
import spring.course.restdocs.web.model.BeerStyle;

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
            get("/api/v1/beer/" + id.toString()).accept(APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    void saveNewBeer() throws Exception {
        BeerDto dto = getValidBeerDto();
        String beerDtoJson = mapper.writeValueAsString(dto);

        mockMvc.perform(
            post("/api/v1/beer/")
                .contentType(APPLICATION_JSON)
                .content(beerDtoJson)
        ).andExpect(status().isCreated());
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