package vn.com.cmcglobal.demoshopcart.Product.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.com.cmcglobal.demoshopcart.Product.model.Category;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchProductRequest {
    private int id;
    private String name;
    private Long price;
    private Category category;
    private int offset;
    private int limit;
}
