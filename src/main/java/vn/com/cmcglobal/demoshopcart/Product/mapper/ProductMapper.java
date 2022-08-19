package vn.com.cmcglobal.demoshopcart.Product.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import vn.com.cmcglobal.demoshopcart.Product.dto.SearchProductRequest;
import vn.com.cmcglobal.demoshopcart.Product.model.Product;

import java.util.List;

@Mapper
public interface ProductMapper {
    final String GET_ALL = "SELECT * FROM PRODUCT";

    @Select(GET_ALL)
    @Results(value = {@Result(property = "id", column = "ID"),
            @Result(property = "name", column = "NAME"),
            @Result(property = "price", column = "PRICE"),
            @Result(property = "amount", column = "AMOUNT"),
            @Result(property = "category", column = "CATEGORY"),
            @Result(property = "createAt", column = "CREATE_AT"),
            @Result(property = "updateAt", column = "UPDATE_AT")})
    public List<Product> getAll();

    // get all product pagination
    final String GET_ALL_PRODUCT = "SELECT * FROM PRODUCT LIMIT #{offset}, #{limit};";

    @Select(GET_ALL_PRODUCT)
    @Results(value = {@Result(property = "id", column = "ID"),
            @Result(property = "name", column = "NAME"),
            @Result(property = "price", column = "PRICE"),
            @Result(property = "amount", column = "AMOUNT"),
            @Result(property = "category", column = "CATEGORY"),
            @Result(property = "createAt", column = "CREATE_AT"),
            @Result(property = "updateAt", column = "UPDATE_AT")})
    public List<Product> getAllPagiNation(int offset, int limit);

    // get product by id
    final String GET_PRODUCT_BY_ID = "SELECT * FROM PRODUCT WHERE ID = #{id}";

    @Results(value = {@Result(property = "id", column = "ID"),
            @Result(property = "name", column = "NAME"),
            @Result(property = "price", column = "PRICE"),
            @Result(property = "amount", column = "AMOUNT"),
            @Result(property = "category", column = "CATEGORY"),
            @Result(property = "createAt", column = "CREATE_AT"),
            @Result(property = "updateAt", column = "UPDATE_AT")})
    @Select(GET_PRODUCT_BY_ID)
    public Product getById(int id);

    // search product by name - price - category
    final String SEARCH_PRODUCT = "SELECT * FROM PRODUCT WHERE name LIKE '%' #{param.name} '%' LIMIT #{param.offset}, #{param.limit};";

    @Select(SEARCH_PRODUCT)
    public List<Product> searchByUser(@Param("param") SearchProductRequest searchProductRequest);

    // inert product
    final String INSERT_PRODUCT = "INSERT INTO PRODUCT (ID, NAME, PRICE,AMOUNT,CATEGORY,CREATE_AT,UPDATE_AT) VALUES (#{id}, #{name}, #{price}, #{amount}, #{category}, #{createAt}, #{updateAt})";

    @Update(INSERT_PRODUCT)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(Product product);


    // update user
    final String UPDATE_PRODUCT = "UPDATE PRODUCT SET NAME = #{name}, PRICE = #{price}, AMOUNT = #{amount}, UPDATE_AT = #{updateAt} WHERE ID = #{id}";

    @Insert(UPDATE_PRODUCT)
    public void update(Product product);

    // delete product by id
    final String DELETE_PRODUCT_BY_ID = "DELETE from PRODUCT WHERE ID = #{id}";

    @Delete(DELETE_PRODUCT_BY_ID)
    public void delete(int id);
}
