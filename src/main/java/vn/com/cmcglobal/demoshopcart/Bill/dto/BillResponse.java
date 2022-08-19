package vn.com.cmcglobal.demoshopcart.Bill.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.com.cmcglobal.demoshopcart.Bill.model.BillOrder;
import vn.com.cmcglobal.demoshopcart.Cart.dto.CartLineDto;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BillResponse {
    private int id;
    private long total;
    private List<CartLineDto> cartLineDtoList;

    public BillResponse(BillOrder billOrder, List<CartLineDto> cartLineDtoList) {
        this.id = billOrder.getId();
        this.total = billOrder.getTotal();
        this.cartLineDtoList = cartLineDtoList;
    }
}
