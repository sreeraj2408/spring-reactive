package com.reactivelearning.util;

import com.reactivelearning.domain.Product;
import com.reactivelearning.dto.ProductDto;
import org.springframework.beans.BeanUtils;

public class ProductUtil {

    public static Product mapToDomain(ProductDto productDto) {
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);
        return product;
    }

    public static ProductDto mapToDto(Product product) {
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(product, productDto);
        return productDto;
    }
}
