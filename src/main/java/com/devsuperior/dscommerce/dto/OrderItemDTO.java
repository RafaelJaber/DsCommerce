package com.devsuperior.dscommerce.dto;

import com.devsuperior.dscommerce.entities.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {

    private Long productId;
    private String name;
    private Double price;
    private Integer quantity;

    public OrderItemDTO(OrderItem orderItem) {
        this.productId = orderItem.getProduct().getId();
        this.name = orderItem.getProduct().getName();
        this.price = orderItem.getPrice();
        this.quantity = orderItem.getQuantity();
    }

    public Double getSubTotal() {
        return price * quantity;
    }
}
