package spring.course.mssc.brewery.client.web.client;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import spring.course.mssc.brewery.client.web.model.BeerDto;

@SpringBootTest
class BreweryClientTest {

    @Autowired
    private BreweryClient client;

    @Test
    void getBeerById() {
        BeerDto dto = client.getBeerById(UUID.randomUUID());

        assertThat(dto).isNotNull();
    }

    @Test
    void saveNewBeer() {
        BeerDto dto = BeerDto.builder().beerName("New beer").build();

        URI uri = client.saveNewBeer(dto);

        assertThat(uri).isNotNull();
    }

    @Test
    void updateBeer() {
        UUID id = UUID.randomUUID();
        BeerDto dto = BeerDto.builder().beerName("Updated name").build();

        client.updateBeer(id, dto);
    }

    @Test
    void deleteBeer() {
        UUID id = UUID.randomUUID();

        client.deleteBeer(id);
    }
}