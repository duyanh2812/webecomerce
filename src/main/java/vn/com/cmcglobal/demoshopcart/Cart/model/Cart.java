package vn.com.cmcglobal.demoshopcart.Cart.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Cart {
    private int id;
    private long total;
    private int userId;

    public Cart(int userId) {
        this.userId = userId;
    }
}
