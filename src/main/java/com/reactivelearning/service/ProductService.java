package com.reactivelearning.service;

import com.reactivelearning.dto.ProductDto;
import com.reactivelearning.repository.ProductRepository;
import com.reactivelearning.util.ProductUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public Mono<ProductDto> saveProduct(Mono<ProductDto> productDtoMono) {
        return productDtoMono.map(ProductUtil::mapToDomain)
                .flatMap(repository::save)
                .map(ProductUtil::mapToDto);
    }

    public Flux<ProductDto> findAllProducts() {
        return repository.findAll().map(ProductUtil::mapToDto).delayElements(Duration.ofSeconds(1));
    }
}
