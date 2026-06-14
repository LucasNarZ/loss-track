package com.lucasnarloch.loss_track.domain.product;

import com.lucasnarloch.loss_track.domain.product.dtos.ProductRequestDTO;
import com.lucasnarloch.loss_track.domain.product.dtos.ProductResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponseDTO save(@Valid @RequestBody ProductRequestDTO productRequestDto) {
        return productService.save(productRequestDto);
    }

    @GetMapping("/{id}")
    public ProductResponseDTO findById(@PathVariable UUID id) {
        return productService.findById(id);
    }

    @GetMapping
    public List<ProductResponseDTO> findAll() {
        return productService.findAll();
    }

    @PutMapping("/{id}")
    public ProductResponseDTO update(@PathVariable UUID id, @Valid @RequestBody ProductRequestDTO productRequestDto) {
        return productService.update(id, productRequestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        productService.delete(id);
    }

}
