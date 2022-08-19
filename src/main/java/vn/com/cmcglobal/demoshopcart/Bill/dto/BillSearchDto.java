package vn.com.cmcglobal.demoshopcart.Bill.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillSearchDto {
    private Integer id;
    private Integer userId;
    private Long total;
    private LocalDateTime createAt;
    private int pageIndex;
    private int pageSize;
    private int offset;
}
