package vn.com.cmcglobal.demoshopcart.Cart.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.com.cmcglobal.demoshopcart.Cart.dto.CartLineDto;
import vn.com.cmcglobal.demoshopcart.Cart.dto.CartLineResponse;
import vn.com.cmcglobal.demoshopcart.Cart.mapper.CartLineMapper;
import vn.com.cmcglobal.demoshopcart.Cart.model.CartLine;
import vn.com.cmcglobal.demoshopcart.Cart.model.CartLineStatus;
import vn.com.cmcglobal.demoshopcart.Cart.service.CartLineService;
import vn.com.cmcglobal.demoshopcart.exception.CartLineException;

import java.time.LocalDateTime;

@Service
public class CartLineServiceImpl implements CartLineService {

    @Autowired
    private CartLineMapper cartLineMapper;

    @Override
    public CartLineDto createOrUpdate(CartLineDto dto, Integer id) {
        CartLine cartLine;
        if (id != null) {
            if (dto.getCount() <= 0) {
                cartLineMapper.deleteByIdd(id);
                throw new CartLineException("Đã xoá sản phẩm " + id + " khỏi giỏ hàng");
            }
            cartLine = cartLineMapper.getById(id);
            cartLine.setCount(dto.getCount());
            cartLine.setUpdateAt(LocalDateTime.now());
            cartLine.setStatus(CartLineStatus.WAIT_FOR_PAY);
            cartLineMapper.update(cartLine);
            return new CartLineDto(cartLine);
        }
        this.validateValue(dto);
        cartLine = new CartLine();
        cartLine.setStatus(CartLineStatus.WAIT_FOR_PAY);
        cartLine.setCartId(dto.getCartId());
        cartLine.setProductId(dto.getProductId());
        cartLine.setCount(dto.getCount());
        cartLine.setCreateAt(LocalDateTime.now());
        cartLineMapper.insert(cartLine);
        return new CartLineDto(cartLine);
    }

    @Override
    public int deleteById(int id, Integer cardId) {
        if (cardId != null)
            return cartLineMapper.deleteById(id, cardId);
        return cartLineMapper.deleteByIdd(id);
    }

    @Override
    public CartLineDto getById(int id) {
        return cartLineMapper.getCartLine(id);
    }

    @Override
    public CartLineResponse getCartLineByUserId(int id) {
        return new CartLineResponse(cartLineMapper.getCartLineByUserId(id));
    }

    private void validateValue(CartLineDto dto) {
        if (dto.getCartId() == null)
            throw new CartLineException("cart id is not null");
        if (dto.getProductId() == null)
            throw new CartLineException("product id is not null");
        if (dto.getCount() == null)
            throw new CartLineException("count is not null");
    }
}
