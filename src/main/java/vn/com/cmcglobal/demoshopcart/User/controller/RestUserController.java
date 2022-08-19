package vn.com.cmcglobal.demoshopcart.User.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import vn.com.cmcglobal.demoshopcart.User.dto.LoginRequest;
import vn.com.cmcglobal.demoshopcart.User.dto.UserDto;
import vn.com.cmcglobal.demoshopcart.User.dto.UserSearchDto;
import vn.com.cmcglobal.demoshopcart.User.model.Roles;
import vn.com.cmcglobal.demoshopcart.User.service.UserService;
import vn.com.cmcglobal.demoshopcart.exception.UserException;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class RestUserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Creat Or Update User")
    @PostMapping
    public ResponseEntity<?> create(HttpSession session, @RequestBody UserDto dto) {
        UserDto user = (UserDto) session.getAttribute("user");
        if (user == null || !user.getRole().equals(Roles.ADMIN))
            dto.setRole(Roles.MEMBER);
        UserDto result = userService.createOrUpdate(dto, null);
        return new ResponseEntity<>(result, result != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @Operation(summary = "Update User")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(HttpSession session, @PathVariable int id, @RequestBody UserDto dto) {
        UserDto user = (UserDto) session.getAttribute("user");
        if (user == null)
            throw new UserException("Chua login");
        if (!user.getRole().equals(Roles.ADMIN)) {
            dto.setRole(Roles.MEMBER);
            id = user.getId();
        }
        UserDto result = userService.createOrUpdate(dto, id);
        return new ResponseEntity<>(result, result != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @Operation(summary = "Delete User By Id")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(HttpSession session, @PathVariable int id) {
        UserDto user = (UserDto) session.getAttribute("user");
        if (user == null || !user.getRole().equals(Roles.ADMIN))
            throw new UserException("Khong the xoa user vi ban k phai ADMIN");
        int result = userService.deleteById(id);
        return new ResponseEntity<>(result, result != 0 ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @Operation(summary = "Get Info User By Id")
    @GetMapping
    public ResponseEntity<?> getById(HttpSession session, @RequestParam Integer id) {
        UserDto user = (UserDto) session.getAttribute("user");
        if (user == null)
            throw new UserException("Chưa login");
        if (!user.getRole().equals(Roles.ADMIN))
            id = user.getId();
        UserDto result = userService.getById(id);
        return new ResponseEntity<>(result, result.getUserName() != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @Operation(summary = "Search User Name By Admin")
    @PostMapping("/search")
    public ResponseEntity<?> search(HttpSession session, @RequestBody UserSearchDto dto) {
        UserDto user = (UserDto) session.getAttribute("user");
        if (user == null || !user.getRole().equals(Roles.ADMIN))
            throw new UserException("Khong the tra cứu user");
        List<UserDto> result = userService.search(dto);
        return new ResponseEntity<>(result, result != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @Operation(summary = "Login")
    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, HttpSession session) {
        UserDto user = userService.login(loginRequest.getUsername(), loginRequest.getPassword());
        session.setAttribute("user",
                UserDto.builder()
                        .id(user.getId())
                        .userName(user.getUserName())
                        .fullName(user.getFullName())
                        .mobile(user.getMobile())
                        .email(user.getEmail())
                        .address(user.getAddress())
                        .role(user.getRole())
                        .cartId(user.getCartId())
                        .createAt(user.getUpdateAt())
                        .build());
        return ResponseEntity.ok().body("Đăng nhập thành công !");
    }

    @Operation(summary = "Logout")
    @GetMapping("logout")
    public ResponseEntity<?> logout(HttpSession session) {
        if (session.getAttribute("user") == null) {
            return ResponseEntity.ok().body("Đã đăng nhập đâu mà đòi đăng xuất :D");
        }
        session.removeAttribute("user");
        return ResponseEntity.ok().body("Đã đăng xuất khỏi trái đất !");
    }


}
