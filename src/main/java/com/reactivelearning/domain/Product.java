package com.reactivelearning.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    private Integer id;
    private String name;
    private Double price;
    private Integer quantity;
}
