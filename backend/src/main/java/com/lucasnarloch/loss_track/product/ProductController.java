package com.lucasnarloch.loss_track.product;

import jakarta.validation.Valid;
import org.apache.catalina.LifecycleState;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    
    @PostMapping
    public Product save(@Valid @RequestBody ProductDTO productDto) {
        return productService.save(productDto);
    }

    @GetMapping("/{id}")
    public Product findById(@PathVariable UUID id) {
        return productService.findById(id);
    }

    @GetMapping
    public List<Product> findAll() {
        return productService.findAll();
    }

}
