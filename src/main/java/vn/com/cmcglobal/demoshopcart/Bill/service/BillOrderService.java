package vn.com.cmcglobal.demoshopcart.Bill.service;

import java.util.List;

import vn.com.cmcglobal.demoshopcart.Bill.dto.BillResponse;
import vn.com.cmcglobal.demoshopcart.Bill.dto.BillSearchDto;
import vn.com.cmcglobal.demoshopcart.Bill.dto.ReqBillDto;
import vn.com.cmcglobal.demoshopcart.Bill.model.BillOrder;

public interface BillOrderService {

    BillResponse create(int id, int cartId);
    List<BillOrder> getAllBillOrByMember(ReqBillDto dto);
    List<BillOrder> search(BillSearchDto dto);
}
