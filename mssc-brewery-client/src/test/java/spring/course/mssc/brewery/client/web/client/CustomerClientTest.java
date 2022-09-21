package spring.course.mssc.brewery.client.web.client;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import spring.course.mssc.brewery.client.web.model.CustomerDto;

@SpringBootTest
class CustomerClientTest {
    @Autowired
    private CustomerClient client;

    @Test
    void getCustomerById() {
        CustomerDto dto = client.getCustomerById(UUID.randomUUID());

        assertThat(dto).isNotNull();
    }

    @Test
    void saveNewCustomer() {
        CustomerDto dto = CustomerDto.builder()
            .name("TEST")
            .build();

        URI uri = client.saveNewCustomer(dto);

        assertThat(uri).isNotNull();
    }

    @Test
    void updateCustomer() {
        UUID id = UUID.randomUUID();
        CustomerDto dto = CustomerDto.builder()
            .name("TEST")
            .build();

        client.updateCustomer(id, dto);
    }

    @Test
    void deleteCustomer() {
        client.deleteCustomer(UUID.randomUUID());
    }
}