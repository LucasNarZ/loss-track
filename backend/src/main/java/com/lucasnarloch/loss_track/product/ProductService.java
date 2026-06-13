package com.lucasnarloch.loss_track.product;

import com.lucasnarloch.loss_track.product.exceptions.ProductNotFound;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product save(ProductDTO productDto) {
        Product product = new Product(productDto.name(), productDto.barcode(), productDto.category());
        return productRepository.save(product);
    }

    public Product findById(UUID id) {
        return productRepository.findById(id).orElseThrow(ProductNotFound::new);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
