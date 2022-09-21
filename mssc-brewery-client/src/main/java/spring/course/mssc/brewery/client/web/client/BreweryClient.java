package spring.course.mssc.brewery.client.web.client;

import java.net.URI;
import java.util.UUID;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import spring.course.mssc.brewery.client.web.model.BeerDto;

@Component
@ConfigurationProperties(value = "sfg.brewery", ignoreUnknownFields = false)
public class BreweryClient {
    public final String BEER_PATH_V1 = "/api/v1/beer/";

    private final RestTemplate restTemplate;
    private String apiHost;

    public BreweryClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public BeerDto getBeerById(UUID id) {
        return restTemplate.getForObject(apiHost + BEER_PATH_V1 + id.toString(), BeerDto.class);
    }

    public URI saveNewBeer(BeerDto dto) {
        return restTemplate.postForLocation(apiHost + BEER_PATH_V1, dto);
    }

    public void updateBeer(UUID id, BeerDto dto) {
        restTemplate.put(apiHost + BEER_PATH_V1 + id.toString(), dto);
    }

    public void deleteBeer(UUID id) {
        restTemplate.delete(apiHost + BEER_PATH_V1 + id.toString());
    }

    public void setApiHost(String apiHost) {
        this.apiHost = apiHost;
    }
}
