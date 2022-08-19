package vn.com.cmcglobal.demoshopcart.Cart.provider;

import org.apache.ibatis.jdbc.SQL;

import vn.com.cmcglobal.demoshopcart.Cart.model.CartLine;

public class CartLineProvider {

    public String queryUpdate(CartLine param) {
        SQL sql = new SQL().UPDATE("CART_LINE");
        if (param.getBillOrderId() != null)
            sql.SET(" BILL_ORDER_ID = #{billOrderId}");
        if (param.getCount() != null)
            sql.SET(" COUNT = #{count}");
        if (param.getStatus() != null)
            sql.SET(" STATUS = #{status}");
        if (param.getUpdateAt() != null)
            sql.SET(" UPDATE_AT = #{updateAt}");
        sql.WHERE("CART_ID = #{cartId} AND STATUS = 'WAIT_FOR_PAY'");

        return sql.toString();
    }
}
