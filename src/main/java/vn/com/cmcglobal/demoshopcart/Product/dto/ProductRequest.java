package vn.com.cmcglobal.demoshopcart.Product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.com.cmcglobal.demoshopcart.Product.model.Category;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequest {
    private int id;
    private String name;
    private long price;
    private long amount;
    private Category category;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
