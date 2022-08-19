package vn.com.cmcglobal.demoshopcart.Bill.provider;

import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

import vn.com.cmcglobal.demoshopcart.Bill.dto.BillSearchDto;
import vn.com.cmcglobal.demoshopcart.Bill.dto.ReqBillDto;

public class BillOrderProvider {
    public String search(BillSearchDto param) {
        SQL sql = new SQL().SELECT("ID,USER_ID,TOTAL,CREATE_AT").FROM("BILL_ORDER");
        if (param.getId() != null)
            sql.WHERE(" ID = #{id} ");
        if (param.getUserId() != null)
            sql.WHERE("USER_ID = #{userId}");
        if (param.getTotal() != null)
            sql.WHERE("TOTAL = #{total}");
        if (param.getCreateAt() != null)
            sql.WHERE("CREATE_AT = #{createAt}");
        sql.ORDER_BY("ID ASC ");
        sql.LIMIT(param.getPageSize());
        sql.OFFSET(param.getOffset());
        SQL x = new SQL();
        return sql.toString();
    }

    public String getAllBillOrByMember(ReqBillDto param) {
        SQL sql = new SQL().SELECT("ID,USER_ID,TOTAL,CREATE_AT").FROM("BILL_ORDER");
        if (param.getUserId() != null)
            sql.WHERE(" USER_ID = #{userId} ");
        sql.ORDER_BY("ID ASC ");
        sql.LIMIT(param.getPageSize());
        sql.OFFSET(param.getOffset());
        return sql.toString();
    }
}
