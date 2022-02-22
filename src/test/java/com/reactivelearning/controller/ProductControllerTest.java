package com.reactivelearning.controller;

import com.reactivelearning.dto.ProductDto;
import com.reactivelearning.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebFluxTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    WebTestClient client;

    @MockBean
    ProductService service;

    @Test
    public void shouldSaveOneProduct() {
        ProductDto productDto = ProductDto.builder().name("iPhone 13").price(100000D).quantity(10).build();

        when(service.saveProduct(any()))
                .thenReturn(Mono.just(new ProductDto(1, "iPhone 13", 100000D, 10)));

        client.post()
                .uri("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(ProductDto.class)
                .value(dto -> dto.getName(), equalTo(productDto.getName()));
    }

    @Test
    public void shouldListAllProducts() {
        ProductDto productDto1 = ProductDto.builder().name("iPhone 13").price(100000D).quantity(10).build();
        ProductDto productDto2 = ProductDto.builder().name("Levis Jeans").price(3000D).quantity(20).build();

        when(service.findAllProducts()).thenReturn(Flux.just().just(productDto1, productDto2));

        client.get()
                .uri("/products")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(ProductDto[].class)
                .value(prodArr -> prodArr.length, equalTo(2));
    }
}