package vn.com.cmcglobal.demoshopcart.Cart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.com.cmcglobal.demoshopcart.Cart.model.CartLine;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartLineDto {
    private int id;
    private Integer cartId;
    private Integer userId;
    private Integer productId;
    private String productName;
    private long price;
    private Integer count;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public CartLineDto(CartLine cartLine) {
        this.id = cartLine.getId();
        this.cartId = cartLine.getCartId();
        this.productId = cartLine.getProductId();
        this.count = cartLine.getCount();
        this.createAt = cartLine.getCreateAt();
        this.updateAt = cartLine.getUpdateAt();
    }
}
