package vn.com.cmcglobal.demoshopcart.Cart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import vn.com.cmcglobal.demoshopcart.exception.CartLineException;
import vn.com.cmcglobal.demoshopcart.Cart.dto.CartLineDto;
import vn.com.cmcglobal.demoshopcart.Cart.dto.CartLineResponse;
import vn.com.cmcglobal.demoshopcart.Cart.service.CartLineService;
import vn.com.cmcglobal.demoshopcart.User.dto.UserDto;
import vn.com.cmcglobal.demoshopcart.User.model.Roles;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/cart-line")
public class RestCartLineController {

    @Autowired
    private CartLineService cartLineService;
    @Operation(summary = "Creat CartLine List Product")
    @PostMapping
    public ResponseEntity<?> create(HttpSession session, @RequestBody CartLineDto dto) {
        UserDto user = (UserDto) session.getAttribute("user");
        if (user == null)
            throw new CartLineException("Chưa login");
        if (user.getRole().equals(Roles.ADMIN))
            throw new CartLineException("Admin k the mua sp");
        CartLineDto result = cartLineService.createOrUpdate(dto, null);
        return new ResponseEntity<>(result, result != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @Operation(summary = "Update CartLine List Product")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(HttpSession session, @PathVariable int id, @RequestBody CartLineDto dto) {
        UserDto user = (UserDto) session.getAttribute("user");
        if (user == null)
            throw new CartLineException("Chưa login");
        if (user.getRole().equals(Roles.ADMIN))
            throw new CartLineException("Admin k the mua sp");
        CartLineDto result = cartLineService.createOrUpdate(dto, id);
        return new ResponseEntity<>(result, result != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @Operation(summary = "Delete CartLine List Product")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(HttpSession session, @PathVariable int id) {
        UserDto user = (UserDto) session.getAttribute("user");
        if (user == null)
            throw new CartLineException("Chưa login");
        int result;
        if (user.getRole().equals(Roles.ADMIN))
            result = cartLineService.deleteById(id, null);
        else result = cartLineService.deleteById(id, user.getCartId());
        return new ResponseEntity<>(result, result != 0 ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @Operation(summary = "Get Info CartLine By Id-Cartline")
    @GetMapping
    public ResponseEntity<?> getById(HttpSession session, @RequestParam int id) {
        UserDto user = (UserDto) session.getAttribute("user");
        if (user == null)
            throw new CartLineException("Chưa login");
        CartLineDto result = cartLineService.getById(id);
        return new ResponseEntity<>(result, result != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @Operation(summary = "Get Info CartLine By Id-User")
    @GetMapping("/get-by-user")
    public ResponseEntity<?> getCartLineByUserId(HttpSession session, @RequestParam Integer id) {
        UserDto user = (UserDto) session.getAttribute("user");
        if (user == null)
            throw new CartLineException("Chưa login");
        if (!user.getRole().equals(Roles.ADMIN))
            id = user.getId();
        CartLineResponse result = cartLineService.getCartLineByUserId(id);

        return new ResponseEntity<>(result, result != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
}
