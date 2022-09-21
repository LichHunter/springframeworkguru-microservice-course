package spring.course.mssc.brewery.client.web.config;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class BlockingRestTemplateCustomizer implements RestTemplateCustomizer {
    private Integer MAX_TOTAL;
    private Integer MAX_PER_ROUTE;
    private Integer CONNECTION_REQUEST_TIMEOUT;
    private Integer SOCKET_TIMEOUT;

    public BlockingRestTemplateCustomizer(@Value("${sfg.max-total}") Integer MAX_TOTAL,
                                          @Value("${sfg.max-per-route}") Integer MAX_PER_ROUTE,
                                          @Value("${sfg.connection-request-timeout}") Integer CONNECTION_REQUEST_TIMEOUT,
                                          @Value("${sfg.socket-timeout}") Integer SOCKET_TIMEOUT) {
        this.MAX_TOTAL = MAX_TOTAL;
        this.MAX_PER_ROUTE = MAX_PER_ROUTE;
        this.CONNECTION_REQUEST_TIMEOUT = CONNECTION_REQUEST_TIMEOUT;
        this.SOCKET_TIMEOUT = SOCKET_TIMEOUT;
    }

    @Override
    public void customize(RestTemplate restTemplate) {
        restTemplate.setRequestFactory(this.clientHttpRequestFactory());
    }

    private ClientHttpRequestFactory clientHttpRequestFactory() {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(MAX_TOTAL);
        connectionManager.setDefaultMaxPerRoute(MAX_PER_ROUTE);

        RequestConfig requestConfig = RequestConfig.custom()
            .setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT)
            .setSocketTimeout(SOCKET_TIMEOUT)
            .build();
        CloseableHttpClient closeableHttpClient = HttpClients.custom()
            .setConnectionManager(connectionManager)
            .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
            .setDefaultRequestConfig(requestConfig)
            .build();
        return new HttpComponentsClientHttpRequestFactory(closeableHttpClient);
    }
}
