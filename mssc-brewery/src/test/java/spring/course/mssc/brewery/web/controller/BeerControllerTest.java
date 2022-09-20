package spring.course.mssc.brewery.web.controller;

import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static spring.course.mssc.brewery.web.controller.BeerControllerTest.TestData.TEST_BEER;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import spring.course.mssc.brewery.web.model.BeerDto;
import spring.course.mssc.brewery.web.service.BeerService;

@WebMvcTest(BeerController.class)
@ExtendWith(SpringExtension.class)
class BeerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BeerService beerService;

    @Test
    void givenBeerId_getBeer_willReturnBeer() throws Exception {
        UUID uuid = UUID.randomUUID();
        given(beerService.getBeerById(uuid)).willReturn(TEST_BEER);

        mockMvc.perform(get("/api/v1/beer/" + uuid))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id", is(TEST_BEER.getId().toString())));
    }

    interface TestData {
        BeerDto TEST_BEER = BeerDto.builder()
            .id(UUID.randomUUID())
            .beerName("TEST")
            .beerStyle("TEST_STYLE")
            .upc(1L)
            .build();
    }
}