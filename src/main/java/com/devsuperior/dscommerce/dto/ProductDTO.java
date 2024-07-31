package com.devsuperior.dscommerce.dto;


import com.devsuperior.dscommerce.entities.Product;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private Long id;

    @NotBlank(message = "Field required")
    @Size(min = 3, max = 80, message = "Name must be 3 to 80 characters long")
    private String name;

    @Size(min = 10, message = "Description must be at least 10 characters long")
    @NotBlank(message = "Field required")
    private String description;

    @Positive(message = "The price must be positive")
    private Double price;

    private String imageUrl;

    public ProductDTO(Product entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.price = entity.getPrice();
        this.imageUrl = entity.getImgUrl();
    }
}
