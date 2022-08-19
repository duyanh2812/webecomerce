package vn.com.cmcglobal.demoshopcart.Cart.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import vn.com.cmcglobal.demoshopcart.Cart.dto.CartLineDto;
import vn.com.cmcglobal.demoshopcart.Cart.model.CartLine;
import vn.com.cmcglobal.demoshopcart.Cart.provider.CartLineProvider;

import java.util.List;

@Mapper
public interface CartLineMapper {
    String GET_BY_ID = "SELECT * FROM CART_LINE WHERE ID = #{id} AND STATUS = 'WAIT_FOR_PAY'";

    @Select(GET_BY_ID)
    CartLine getById(Integer id);

    String GET_CART_LINE = "SELECT c.ID as id , c.CART_ID as cart_id, c.PRODUCT_ID as product_id,"
            + "c.COUNT as count,c.CREATE_AT as create_at,c.UPDATE_AT as update_at,p.NAME as product_name FROM CART_LINE c"
            + " JOIN PRODUCT p ON c.PRODUCT_ID = p.ID "
            + " WHERE c.ID = #{id} AND c.STATUS = 'WAIT_FOR_PAY'";

    @Select(GET_CART_LINE)
    @Results(value = {@Result(property = "productName", column = "product_name")
            , @Result(property = "cartId", column = "cart_id")
            , @Result(property = "productId", column = "product_id")
            , @Result(property = "createAt", column = "create_at")
            , @Result(property = "updateAt", column = "update_at")})
    CartLineDto getCartLine(Integer id);

    String DELETE_BY_ID = "DELETE FROM CART_LINE WHERE ID = #{id} AND CART_ID = #{cartId}";

    @Delete(DELETE_BY_ID)
    Integer deleteById(Integer id, Integer cartId);

    @Delete("DELETE FROM CART_LINE WHERE ID = #{id}")
    Integer deleteByIdd(Integer id);

    String INSERT = "INSERT INTO CART_LINE (ID,STATUS,CART_ID, PRODUCT_ID, COUNT, CREATE_AT,UPDATE_AT) "
            + "VALUES (#{id}, #{status},#{cartId}, #{productId},#{count},#{createAt},#{updateAt})";

    @Update(INSERT)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(CartLine cartLine);

    @UpdateProvider(type = CartLineProvider.class, method = "queryUpdate")
    int update(CartLine cartLine);

    String GET_CART_LINE_BY_USER_ID = "SELECT c.ID as id, cart.USER_ID as user_id, p.PRICE as price , c.CART_ID as cart_id, c.PRODUCT_ID as product_id,"
            + "c.COUNT as count,c.CREATE_AT as create_at,c.UPDATE_AT as update_at,p.NAME as product_name FROM CART_LINE c"
            + " JOIN PRODUCT p ON c.PRODUCT_ID = p.ID "
            + " JOIN CART cart ON cart.ID = c.CART_ID"
            + " WHERE cart.USER_ID = #{id} AND c.STATUS = 'WAIT_FOR_PAY'";

    @Select(GET_CART_LINE_BY_USER_ID)
    @Results(value = {@Result(property = "productName", column = "product_name")
            , @Result(property = "userId", column = "user_id")
            , @Result(property = "price", column = "price")
            , @Result(property = "cartId", column = "cart_id")
            , @Result(property = "productId", column = "product_id")
            , @Result(property = "createAt", column = "create_at")
            , @Result(property = "updateAt", column = "update_at")})
    List<CartLineDto> getCartLineByUserId(int id);
}
