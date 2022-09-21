package spring.course.mssc.brewery.client.web.client;

import java.net.URI;
import java.util.UUID;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import spring.course.mssc.brewery.client.web.model.CustomerDto;

@Component
@ConfigurationProperties(value = "sfg.brewery", ignoreUnknownFields = false)
public class CustomerClient {
    public final String CUSTOMER_PATH_V1 = "/api/v1/customer/";

    private final RestTemplate restTemplate;
    private String apiHost;

    public CustomerClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public CustomerDto getCustomerById(UUID id) {
        return restTemplate.getForObject(apiHost + CUSTOMER_PATH_V1 + id.toString(), CustomerDto.class);
    }

    public URI saveNewCustomer(CustomerDto dto) {
        return restTemplate.postForLocation(apiHost + CUSTOMER_PATH_V1, dto);
    }

    public void updateCustomer(UUID id, CustomerDto dto) {
        restTemplate.put(apiHost + CUSTOMER_PATH_V1 + id.toString(), dto);
    }

    public void deleteCustomer(UUID id) {
        restTemplate.delete(apiHost + CUSTOMER_PATH_V1 + id.toString());
    }

    public void setApiHost(String apiHost) {
        this.apiHost = apiHost;
    }
}
