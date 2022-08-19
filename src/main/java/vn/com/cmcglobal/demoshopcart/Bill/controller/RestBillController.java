package vn.com.cmcglobal.demoshopcart.Bill.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import vn.com.cmcglobal.demoshopcart.exception.BillException;
import vn.com.cmcglobal.demoshopcart.Bill.dto.BillResponse;
import vn.com.cmcglobal.demoshopcart.Bill.dto.BillSearchDto;
import vn.com.cmcglobal.demoshopcart.Bill.dto.ReqBillDto;
import vn.com.cmcglobal.demoshopcart.Bill.model.BillOrder;
import vn.com.cmcglobal.demoshopcart.Bill.service.BillOrderService;
import vn.com.cmcglobal.demoshopcart.User.dto.UserDto;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/api/bill")
public class RestBillController {
    @Autowired
    private BillOrderService billOrderService;

    @Operation(summary = "Creat Bill")
    @PostMapping
    public ResponseEntity<?> create(HttpSession session) {
        UserDto user = (UserDto) session.getAttribute("user");
        if (user == null)
            throw new BillException("Chưa login");
        BillResponse result = billOrderService.create(user.getId(),user.getCartId());
        return new ResponseEntity<>(result, result != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @Operation(summary = "Get Bill")
    @PostMapping("/get-bill")
    public ResponseEntity<?> getAllBillOrByMember(@RequestBody ReqBillDto dto){
        List<BillOrder> billOrders = this.billOrderService.getAllBillOrByMember(dto);
        return new ResponseEntity<>(billOrders, billOrders == null ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
    }

    @Operation(summary = "Search Bill")
    @PostMapping("/search")
    public ResponseEntity<?> search(@RequestBody BillSearchDto dto){
        List<BillOrder> billOrders = this.billOrderService.search(dto);
        return new ResponseEntity<>(billOrders, billOrders == null ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
    }
}
