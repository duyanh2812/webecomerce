package vn.com.cmcglobal.demoshopcart.User.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import vn.com.cmcglobal.demoshopcart.User.dto.UserDto;
import vn.com.cmcglobal.demoshopcart.User.dto.UserSearchDto;
import vn.com.cmcglobal.demoshopcart.User.model.User;
import vn.com.cmcglobal.demoshopcart.User.provider.UserProvider;

import java.util.List;

public interface UserMapper {

    // get user by id
    String GET_USER_BY_ID = "SELECT ID,USER_NAME,PASSWORD,FULL_NAME,MOBILE,EMAIL,ADDRESS,ROLE,CREATE_AT,UPDATE_AT FROM USER WHERE ID = #{id}";
    // get user by username
    final String GET_USER_BY_USERNAME = "SELECT u.ID as id,u.USER_NAME as userName, u.FULL_NAME as fullName, u.ROLE as role, u.STATUS as status, u.PASSWORD as password, c.id as cartId " +
            "FROM USER u JOIN CART c on u.id = c.user_id WHERE u.USER_NAME = #{userName} AND u.STATUS = 'ACTIVE'";

    @Select(GET_USER_BY_USERNAME)
    UserDto getByUsername(String userName);

    @Select(GET_USER_BY_ID)
    @Results(value = {@Result(property = "id", column = "ID"),
            @Result(property = "userName", column = "USER_NAME"),
            @Result(property = "password", column = "PASSWORD"),
            @Result(property = "fullName", column = "FULL_NAME"),
            @Result(property = "mobile", column = "MOBILE"),
            @Result(property = "email", column = "EMAIL"),
            @Result(property = "address", column = "ADDRESS"),
            @Result(property = "role", column = "ROLE"),
            @Result(property = "createAt", column = "CREATE_AT"),
            @Result(property = "updateAt", column = "UPDATE_AT")})
    User getById(int id);

    // inert user
    String INSERT_USER = "INSERT INTO USER (ID, USER_NAME, PASSWORD,FULL_NAME,MOBILE, EMAIL,ADDRESS,ROLE,STATUS,CREATE_AT,UPDATE_AT) "
            + "VALUES (#{id}, #{userName}, #{password},#{fullName}, #{mobile}, #{email},#{address},#{role},#{status},#{createAt},#{updateAt})";

    @Update(INSERT_USER)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(User user);

    @UpdateProvider(type = UserProvider.class, method = "queryUpdate")
    int update(User user);

//     // delete user by id
    String DELETE_USER_BY_ID = "UPDATE USER SET STATUS = #{status} WHERE ID = #{id}";
    @Delete(DELETE_USER_BY_ID)
    int delete(String status,int id);

    String COUNT_USER_BY_USERNAME = "SELECT COUNT(*) FROM USER WHERE USER_NAME = #{userName}";

    @Select(COUNT_USER_BY_USERNAME)
    int countByUserName(String userName);

    @SelectProvider(type = UserProvider.class, method = "queryOrderByParam")
    @Results(value = {@Result(property = "id", column = "ID"),
            @Result(property = "userName", column = "USER_NAME"),
            @Result(property = "fullName", column = "FULL_NAME"),
            @Result(property = "mobile", column = "MOBILE"),
            @Result(property = "email", column = "EMAIL"),
            @Result(property = "address", column = "ADDRESS"),
            @Result(property = "role", column = "ROLE"),
            @Result(property = "createAt", column = "CREATE_AT"),
            @Result(property = "updateAt", column = "UPDATE_AT")})
    List<User> search(UserSearchDto user);
}
