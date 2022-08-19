package vn.com.cmcglobal.demoshopcart.Bill.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqBillDto {
    private Integer userId;
    private int pageIndex;
    private int pageSize;
    private int offset;
}
