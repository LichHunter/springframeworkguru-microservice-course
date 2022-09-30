package spring.cource.mssc.jackson.examples.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;

@JsonTest
class BeerDtoTest extends BaseTest {
    @Autowired
    private ObjectMapper mapper;

    @Test
    void testSerializeDto() throws JsonProcessingException {
        BeerDto dto = beerDto();

        String json = mapper.writeValueAsString(dto);

        System.out.println(json);
    }

    @Test
    void testDeserialization() throws JsonProcessingException {
        String json = "{\"id\":\"8c9d3e5f-b37c-436a-b510-6811aad91a15\",\"beerName\":\"BeerName\",\"beerStyle\":\"Ale\",\"upc\":1,\"price\":12.99,\"createdDate\":\"2022-09-30T17:07:18.43949+02:00\",\"lastUpdatedDate\":\"2022-09-30T17:07:18.4402+02:00\"}";

        BeerDto dto = mapper.readValue(json, BeerDto.class);

        System.out.println(dto);
    }
}