package com.reactivelearning.controller;

import com.reactivelearning.dto.ProductDto;
import com.reactivelearning.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping
    public ResponseEntity<Mono<ProductDto>> saveProduct(@RequestBody Mono<ProductDto> productDtoMono) {
        return ResponseEntity.ok(service.saveProduct(productDtoMono));
    }

    @GetMapping
    public Flux<ProductDto> getAllProducts() {
        return service.findAllProducts();
    }

    @GetMapping(value = "/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ProductDto> getAllProductEvents() {
        return service.findAllProducts();
    }
}
