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
        String json = "{\"beerName\":\"BeerName\",\"beerStyle\":\"Ale\",\"upc\":1,\"price\":\"12.99\",\"createdDate\":\"2022-09-30T17:45:34+0200\",\"lastUpdatedDate\":\"2022-09-30T17:45:34.859255+02:00\",\"date\":\"20220930\",\"beerId\":\"a10685af-d956-49d1-a78f-df01e9e0330a\"}\n";

        BeerDto dto = mapper.readValue(json, BeerDto.class);

        System.out.println(dto);
    }
}