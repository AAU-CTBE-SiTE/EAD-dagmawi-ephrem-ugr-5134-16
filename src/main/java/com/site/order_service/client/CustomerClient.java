package com.site.order_service.client;


import com.site.order_service.dto.CustomerResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import org.springframework.web.server.ResponseStatusException;

@Component
public class CustomerClient {

    private final RestClient restClient;

    public CustomerClient(
            @Qualifier("customerRestClient") RestClient restClient) {
        this.restClient = restClient;
    }

    public CustomerResponse getCustomerById(String customerId) {

        try {
            return restClient.get()
                    .uri("/api/customers/{id}", customerId)
                    .retrieve()
                    .body(CustomerResponse.class);

        } catch (HttpClientErrorException.NotFound ex) {

            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Customer not found: " + customerId
            );

        } catch (RestClientException ex) {

            throw new ResponseStatusException(
                    HttpStatus.SERVICE_UNAVAILABLE,
                    "Customer Service is unavailable"
            );
        }
    }
}