package vn.com.cmcglobal.demoshopcart.User.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.com.cmcglobal.demoshopcart.User.model.Roles;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSearchDto {
    private String userName;
    private String fullName;
    private String mobile;
    private String email;
    private String address;
    private Roles role;
    private int pageIndex;
    private int pageSize;
    private int offset;
}
