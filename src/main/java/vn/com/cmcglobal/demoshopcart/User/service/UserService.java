package vn.com.cmcglobal.demoshopcart.User.service;

import java.util.List;

import vn.com.cmcglobal.demoshopcart.User.dto.UserDto;
import vn.com.cmcglobal.demoshopcart.User.dto.UserSearchDto;

public interface UserService {
    UserDto createOrUpdate(UserDto dto, Integer id);

    Integer deleteById(Integer id);

    UserDto getById(int id);

    List<UserDto> search(UserSearchDto dto);

    UserDto login(String username, String password);

}