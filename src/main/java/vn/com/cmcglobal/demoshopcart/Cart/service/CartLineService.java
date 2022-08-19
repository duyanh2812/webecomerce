package vn.com.cmcglobal.demoshopcart.Cart.service;

import vn.com.cmcglobal.demoshopcart.Cart.dto.CartLineDto;
import vn.com.cmcglobal.demoshopcart.Cart.dto.CartLineResponse;

public interface CartLineService {

    CartLineDto createOrUpdate(CartLineDto dto, Integer id);

    int deleteById(int id, Integer cardId);

    CartLineDto getById(int id);

    CartLineResponse getCartLineByUserId(int id);
}
