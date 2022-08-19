package vn.com.cmcglobal.demoshopcart.User.provider;

import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

import vn.com.cmcglobal.demoshopcart.User.dto.UserSearchDto;
import vn.com.cmcglobal.demoshopcart.User.model.User;

public class UserProvider {
    public String queryOrderByParam(UserSearchDto param) {
        SQL sql = new SQL().SELECT("ID,USER_NAME,FULL_NAME,MOBILE,EMAIL,ADDRESS,ROLE,CREATE_AT,UPDATE_AT,STATUS").FROM("USER");

        if (StringUtils.hasText(param.getUserName()))
            sql.WHERE(" USER_NAME like '%' #{userName} '%' ");
        sql.ORDER_BY("ID ASC ");
        sql.LIMIT(param.getPageSize());
        sql.OFFSET(param.getOffset());
        return sql.toString();
    }

    public String queryUpdate(User param) {
        SQL sql = new SQL().UPDATE("USER");
        if (StringUtils.hasText(param.getUserName()))
            sql.SET(" USER_NAME = #{userName}");
        if (StringUtils.hasText(param.getPassword()))
            sql.SET(" PASSWORD = #{password}");
        if (StringUtils.hasText(param.getFullName()))
            sql.SET(" FULL_NAME = #{fullName}");
        if (StringUtils.hasText(param.getMobile()))
            sql.SET(" MOBILE = #{mobile}");
        if (StringUtils.hasText(param.getEmail()))
            sql.SET(" EMAIL = #{email}");
        if (StringUtils.hasText(param.getAddress()))
            sql.SET(" ADDRESS = #{address}");
        if (param.getRole() != null)
            sql.SET(" ROLE = #{role}");
        if (param.getStatus() != null)
            sql.SET(" STATUS = #{status}");
        if (param.getUpdateAt() != null)
            sql.SET(" UPDATE_AT = #{updateAt}");
        sql.WHERE("ID = #{id}");
        System.out.printf(sql.toString());
        return sql.toString();
    }
}
