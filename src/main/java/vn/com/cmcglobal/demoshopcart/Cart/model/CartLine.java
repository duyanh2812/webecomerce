package vn.com.cmcglobal.demoshopcart.Cart.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartLine {
    private int id;
    private Integer cartId;
    private Integer productId;
    private Integer billOrderId;
    private Integer count;
    private CartLineStatus status;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public CartLine(int billOrderId, int cartId, LocalDateTime updateAt, CartLineStatus status) {
        this.billOrderId = billOrderId;
        this.updateAt = updateAt;
        this.status = status;
        this.cartId = cartId;
    }
}
