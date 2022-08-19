package vn.com.cmcglobal.demoshopcart.Bill.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import vn.com.cmcglobal.demoshopcart.Bill.dto.BillSearchDto;
import vn.com.cmcglobal.demoshopcart.Bill.dto.ReqBillDto;
import vn.com.cmcglobal.demoshopcart.Bill.model.BillOrder;
import vn.com.cmcglobal.demoshopcart.Bill.provider.BillOrderProvider;
import vn.com.cmcglobal.demoshopcart.User.provider.UserProvider;

import java.util.List;


@Mapper
public interface BillOrderMapper {
    String INSERT = "INSERT INTO BILL_ORDER (ID,USER_ID,TOTAL,CREATE_AT) "
            + "VALUES (#{id}, #{userId}, #{total},#{createAt})";

    @Update(INSERT)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int create(BillOrder billOrder);

    @SelectProvider(type = BillOrderProvider.class, method = "search")
    @Results(value = {
            @Result(property = "id", column = "ID"),
            @Result(property = "userId", column = "USER_ID"),
            @Result(property = "total", column = "TOTAL"),
            @Result(property = "createAt", column = "CREATE_AT")})
    List<BillOrder> search(BillSearchDto dto);

    @SelectProvider(type = BillOrderProvider.class, method = "getAllBillOrByMember")
    @Results(value = {
            @Result(property = "id", column = "ID"),
            @Result(property = "userId", column = "USER_ID"),
            @Result(property = "total", column = "TOTAL"),
            @Result(property = "createAt", column = "CREATE_AT")})
    List<BillOrder> getAllBillOrByMember(ReqBillDto dto);
}
