package vn.com.cmcglobal.demoshopcart.Product.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class Product {
    private int id;
    private String name;
    private long price;
    private long amount;
    private Category category;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
