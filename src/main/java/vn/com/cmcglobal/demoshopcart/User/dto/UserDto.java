package vn.com.cmcglobal.demoshopcart.User.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.com.cmcglobal.demoshopcart.User.model.Roles;
import vn.com.cmcglobal.demoshopcart.User.model.User;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private int id;
    private String userName;
    private String password;
    private String confirmPassword;
    private String fullName;
    private String mobile;
    private String email;
    private String address;
    private Roles role;
    private String status;
    private int cartId;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public UserDto(User user) {
        this.id = user.getId();
        this.userName = user.getUserName();
        this.fullName = user.getFullName();
        this.mobile = user.getMobile();
        this.email = user.getEmail();
        this.address = user.getAddress();
        this.role = user.getRole();
        this.status = user.getStatus();
        this.createAt = user.getCreateAt();
        this.updateAt = user.getUpdateAt();
    }
}
