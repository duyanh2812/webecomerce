package vn.com.cmcglobal.demoshopcart.User.service.serviceImpl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import vn.com.cmcglobal.demoshopcart.exception.UserException;
import vn.com.cmcglobal.demoshopcart.Cart.mapper.CartMapper;
import vn.com.cmcglobal.demoshopcart.Cart.model.Cart;
import vn.com.cmcglobal.demoshopcart.User.dto.UserDto;
import vn.com.cmcglobal.demoshopcart.User.dto.UserSearchDto;
import vn.com.cmcglobal.demoshopcart.User.mapper.UserMapper;
import vn.com.cmcglobal.demoshopcart.User.model.Roles;
import vn.com.cmcglobal.demoshopcart.User.model.User;
import vn.com.cmcglobal.demoshopcart.User.service.UserService;
import vn.com.cmcglobal.demoshopcart.exception.BadRequestException;
import vn.com.cmcglobal.demoshopcart.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CartMapper cartMapper;

    @Override
    public UserDto createOrUpdate(UserDto dto, Integer id) {
        User user;
        if (StringUtils.hasText(dto.getPassword()) || StringUtils.hasText(dto.getConfirmPassword()))
            if (!dto.getPassword().equals(dto.getConfirmPassword()))
                throw new UserException("Confirm pass error");
        if (id != null) {
            user = userMapper.getById(id);
            if (user == null)
                throw new UserException("User not exist");
            user.setUpdateAt(LocalDateTime.now());
            user = this.setProperties(dto, user);
            userMapper.update(user);
            return new UserDto(user);
        } else {
            int count = userMapper.countByUserName(dto.getUserName());
            if (count == 0) {
                user = new User();
                user.setCreateAt(LocalDateTime.now());
                user = this.setProperties(dto, user);
                userMapper.insert(user);
                if (!user.getRole().equals(Roles.ADMIN))
                    cartMapper.insert(new Cart(user.getId()));
                return new UserDto(user);
            } else {
                throw new UserException("UserName is exist");
            }
        }
    }

    private User setProperties(UserDto dto, User user) {
        return User.builder()
                .id(user.getId())
                .userName(dto.getUserName() != null ? dto.getUserName() : user.getUserName())
                .password(dto.getPassword() != null ? dto.getPassword() : user.getPassword())
                .fullName(dto.getFullName() != null ? dto.getFullName() : user.getFullName())
                .mobile(dto.getMobile() != null ? dto.getMobile() : user.getMobile())
                .email(dto.getEmail() != null ? dto.getEmail() : user.getEmail())
                .address(dto.getAddress() != null ? dto.getAddress() : user.getAddress())
                .role(dto.getRole() != null ? dto.getRole() : user.getRole())
                .status("ACTIVE")
                .createAt(user.getCreateAt())
                .updateAt(user.getUpdateAt())
                .build();
    }

    @Override
    public Integer deleteById(Integer id) {
        return userMapper.delete("UNACTIVE", id);
    }

    @Override
    public UserDto getById(int id) {
        User user = userMapper.getById(id);
        UserDto result = new UserDto();
        if (user != null)
            BeanUtils.copyProperties(user, result);
        return result;
    }

    @Override
    public List<UserDto> search(UserSearchDto dto) {
        int pageIndex, pageSize;
        pageIndex = dto.getPageIndex() > 0 ? dto.getPageIndex() - 1 : 0;
        pageSize = dto.getPageSize() <= 0 ? 10 : dto.getPageSize();
        int offset = pageIndex * pageSize;
        dto.setPageSize(pageSize);
        dto.setOffset(offset);
        List<User> userList = userMapper.search(dto);
        List<UserDto> result = userList.stream().map(UserDto::new).collect(Collectors.toList());
        return result;
    }

    @Override
    public UserDto login(String username, String password) {
        UserDto o_user = userMapper.getByUsername(username);
        if (o_user == null) {
            throw new NotFoundException("User is not found");
        }

        if (!o_user.getStatus().equals("ACTIVE")) {
            throw new UserException("User is not active");
        }

        // Kiểm tra password
        if (o_user.getPassword().equals(password)) {
            return o_user;
        } else {
            throw new BadRequestException("Password is incorrect");
        }
    }

}
