package com.devsuperior.dscommerce.services;

import com.devsuperior.dscommerce.dto.ProductDTO;
import com.devsuperior.dscommerce.dto.ProductMinDTO;
import com.devsuperior.dscommerce.entities.Product;
import com.devsuperior.dscommerce.repositories.ProductRepository;
import com.devsuperior.dscommerce.services.exceptions.ResourceNotFoundException;
import com.devsuperior.dscommerce.tests.ProductFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@Tag("Unit")
@ExtendWith(SpringExtension.class)
public class ProductServiceTests {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    private long existingProductId, nonExistingProductId;
    private String productName;
    private Product product;
    private ProductDTO productDTO;
    private PageImpl<Product> productPage;

    @BeforeEach
    void setUp() throws Exception {
        existingProductId = 1L;
        nonExistingProductId = 2L;
        productName = "Playstation 5";
        product = ProductFactory.createProduct(productName);
        productDTO = new ProductDTO(product);
        productPage = new PageImpl<>(List.of(product));

        Mockito.when(productRepository.findById(existingProductId)).thenReturn(Optional.of(product));
        Mockito.when(productRepository.findById(nonExistingProductId)).thenReturn(Optional.empty());
        Mockito.when(productRepository.searchByName(any(), any(Pageable.class))).thenReturn(productPage);
        Mockito.when(productRepository.save(any())).thenReturn(product);
    }

    @Test
    public void findByIdShouldReturnProductDTOWhenIdExists() {
        ProductDTO result = productService.findById(existingProductId);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getId(), existingProductId);
        Assertions.assertEquals(result.getName(), productName);
        Assertions.assertEquals(product.getPrice(), result.getPrice());
    }

    @Test
    public void findByIdShouldThrowsResourceNotFoundExceptionWhenIdDoesNotExist() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            productService.findById(nonExistingProductId);
        });

        Mockito.verify(productRepository, Mockito.times(1)).findById(nonExistingProductId);
    }

    @Test
    public void findByNameShouldReturnProductDTOWhenNameExists() {
        Pageable pageable = PageRequest.of(0, 12);
        Page<ProductMinDTO> result = productService.findAll(productName, pageable);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getSize(), 1);
        Assertions.assertEquals(result.getContent().getFirst().getName(), productName);
    }

    @Test
    public void insertShouldReturnProductDTO() {
        ProductDTO result = productService.insert(productDTO);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getId(), product.getId());
        Assertions.assertEquals(result.getName(), product.getName());
        Assertions.assertEquals(result.getPrice(), product.getPrice());
        Assertions.assertEquals(result.getImageUrl(), product.getImgUrl());
        Assertions.assertEquals(result.getDescription(), product.getDescription());
        Assertions.assertEquals(result.getCategories().getFirst().getId(), product.getCategories().iterator().next().getId());
    }
}
