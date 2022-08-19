package vn.com.cmcglobal.demoshopcart.Cart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartLineResponse {
    private List<CartLineDto> cartLineDtoList;
    private Long total;

    public CartLineResponse(List<CartLineDto> cartLineDtoList) {
        this.cartLineDtoList = cartLineDtoList;
        Long total = 0l;
        for (CartLineDto dto : cartLineDtoList) {
            total += (dto.getPrice() * dto.getCount());
        }
        this.total = total;
    }
}
