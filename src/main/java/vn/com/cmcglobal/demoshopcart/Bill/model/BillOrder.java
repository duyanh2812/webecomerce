package vn.com.cmcglobal.demoshopcart.Bill.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillOrder {
    private int id;
    private int userId;
    private long total;
    private LocalDateTime createAt;
}
