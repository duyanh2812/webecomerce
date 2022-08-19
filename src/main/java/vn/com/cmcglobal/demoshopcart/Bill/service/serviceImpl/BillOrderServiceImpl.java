package vn.com.cmcglobal.demoshopcart.Bill.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.com.cmcglobal.demoshopcart.exception.UserException;
import vn.com.cmcglobal.demoshopcart.Bill.dto.BillResponse;
import vn.com.cmcglobal.demoshopcart.Bill.dto.BillSearchDto;
import vn.com.cmcglobal.demoshopcart.Bill.dto.ReqBillDto;
import vn.com.cmcglobal.demoshopcart.Bill.mapper.BillOrderMapper;
import vn.com.cmcglobal.demoshopcart.Bill.model.BillOrder;
import vn.com.cmcglobal.demoshopcart.Bill.service.BillOrderService;
import vn.com.cmcglobal.demoshopcart.Cart.dto.CartLineDto;
import vn.com.cmcglobal.demoshopcart.Cart.mapper.CartLineMapper;
import vn.com.cmcglobal.demoshopcart.Cart.model.CartLine;
import vn.com.cmcglobal.demoshopcart.Cart.model.CartLineStatus;
import vn.com.cmcglobal.demoshopcart.User.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BillOrderServiceImpl implements BillOrderService {
    @Autowired
    private BillOrderMapper billOrderMapper;
    @Autowired
    private CartLineMapper cartLineMapper;

    @Autowired
    private HttpServletRequest httpServletRequest;


    @Override
    public BillResponse create(int id, int cartId) {
        BillOrder billOrder = new BillOrder();
        long total = 0;
        List<CartLineDto> cartLineDtoList = cartLineMapper.getCartLineByUserId(id);
        for (CartLineDto dto : cartLineDtoList) {
            total += (dto.getCount() * dto.getPrice());
        }
        billOrder.setCreateAt(LocalDateTime.now());
        billOrder.setUserId(id);
        billOrder.setTotal(total);
        billOrderMapper.create(billOrder);
        CartLine cartLine = new CartLine(billOrder.getId(), cartId, LocalDateTime.now(), CartLineStatus.PAYING_SUCCESSFUL);
        cartLineMapper.update(cartLine);
        return new BillResponse(billOrder, cartLineDtoList);
    }

    @Override
    public List<BillOrder> getAllBillOrByMember(ReqBillDto dto) {
        HttpSession session = this.httpServletRequest.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            throw new UserException("Chưa login");
        } else {
            int pageIndex, pageSize;
            pageIndex = dto.getPageIndex() > 0 ? dto.getPageIndex() - 1 : 0;
            pageSize = dto.getPageSize() <= 0 ? 10 : dto.getPageSize();
            int offset = pageIndex * pageSize;
            dto.setPageSize(pageSize);
            dto.setOffset(offset);
            List<BillOrder> billOrders = this.billOrderMapper.getAllBillOrByMember(dto);
            return billOrders;
        }
    }

    @Override
    public List<BillOrder> search(BillSearchDto dto) {
        HttpSession session = this.httpServletRequest.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            throw new UserException("Chưa login");
        } else {
            int pageIndex, pageSize;
            pageIndex = dto.getPageIndex() > 0 ? dto.getPageIndex() - 1 : 0;
            pageSize = dto.getPageSize() <= 0 ? 10 : dto.getPageSize();
            int offset = pageIndex * pageSize;
            dto.setPageSize(pageSize);
            dto.setOffset(offset);
            List<BillOrder> billOrders = this.billOrderMapper.search(dto);
            return billOrders;
        }
    }
}
