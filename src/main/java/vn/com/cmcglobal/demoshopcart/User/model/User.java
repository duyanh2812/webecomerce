package vn.com.cmcglobal.demoshopcart.User.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private int id;
    private String userName;
    private String password;
    private String fullName;
    private String mobile;
    private String email;
    private String address;
    private Roles role;
    private String status;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
