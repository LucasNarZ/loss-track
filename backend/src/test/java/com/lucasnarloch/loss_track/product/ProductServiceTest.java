package com.lucasnarloch.loss_track.product;

import com.lucasnarloch.loss_track.product.dtos.ProductRequestDTO;
import com.lucasnarloch.loss_track.product.dtos.ProductResponseDTO;
import com.lucasnarloch.loss_track.product.exceptions.ProductNotFound;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void saveShouldCreateProductAndReturnResponseDto() {
        ProductRequestDTO request = new ProductRequestDTO("Notebook", "123456", "Eletronicos");

        when(productRepository.save(any(Product.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        ProductResponseDTO response = productService.save(request);

        assertEquals("Notebook", response.name());
        assertEquals("123456", response.barcode());
        assertEquals("Eletronicos", response.category());

        ArgumentCaptor<Product> productCaptor = ArgumentCaptor.forClass(Product.class);
        verify(productRepository).save(productCaptor.capture());

        Product savedProduct = productCaptor.getValue();
        assertEquals("Notebook", savedProduct.getName());
        assertEquals("123456", savedProduct.getBarcode());
        assertEquals("Eletronicos", savedProduct.getCategory());
    }

    @Test
    void findByIdShouldReturnResponseDtoWhenProductExists() {
        UUID id = UUID.randomUUID();
        Product product = new Product("Notebook", "123456", "Eletronicos");

        when(productRepository.findById(id)).thenReturn(Optional.of(product));

        ProductResponseDTO response = productService.findById(id);

        assertEquals("Notebook", response.name());
        assertEquals("123456", response.barcode());
        assertEquals("Eletronicos", response.category());
    }

    @Test
    void findByIdShouldThrowProductNotFoundWhenProductDoesNotExist() {
        UUID id = UUID.randomUUID();

        when(productRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ProductNotFound.class, () -> productService.findById(id));
    }

    @Test
    void findAllShouldReturnResponseDtoList() {
        Product firstProduct = new Product("Notebook", "123456", "Eletronicos");
        Product secondProduct = new Product("Cadeira", "654321", "Moveis");

        when(productRepository.findAll()).thenReturn(List.of(firstProduct, secondProduct));

        List<ProductResponseDTO> response = productService.findAll();

        assertEquals(2, response.size());
        assertEquals("Notebook", response.get(0).name());
        assertEquals("Cadeira", response.get(1).name());
    }

    @Test
    void updateShouldUpdateProductAndReturnResponseDtoWhenProductExists() {
        UUID id = UUID.randomUUID();
        Product product = new Product("Notebook", "123456", "Eletronicos");
        ProductRequestDTO request = new ProductRequestDTO("Monitor", "789012", "Informatica");

        when(productRepository.findById(id)).thenReturn(Optional.of(product));
        when(productRepository.save(product)).thenReturn(product);

        ProductResponseDTO response = productService.update(id, request);

        assertEquals("Monitor", response.name());
        assertEquals("789012", response.barcode());
        assertEquals("Informatica", response.category());
        assertEquals("Monitor", product.getName());
        assertEquals("789012", product.getBarcode());
        assertEquals("Informatica", product.getCategory());

        verify(productRepository).save(product);
    }

    @Test
    void updateShouldThrowProductNotFoundWhenProductDoesNotExist() {
        UUID id = UUID.randomUUID();
        ProductRequestDTO request = new ProductRequestDTO("Monitor", "789012", "Informatica");

        when(productRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ProductNotFound.class, () -> productService.update(id, request));
        verify(productRepository, never()).save(any(Product.class));
    }

    @Test
    void deleteShouldDeleteProductWhenProductExists() {
        UUID id = UUID.randomUUID();
        Product product = new Product("Notebook", "123456", "Eletronicos");

        when(productRepository.findById(id)).thenReturn(Optional.of(product));

        productService.delete(id);

        verify(productRepository).delete(product);
    }

    @Test
    void deleteShouldThrowProductNotFoundWhenProductDoesNotExist() {
        UUID id = UUID.randomUUID();

        when(productRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ProductNotFound.class, () -> productService.delete(id));
        verify(productRepository, never()).delete(any(Product.class));
    }
}
