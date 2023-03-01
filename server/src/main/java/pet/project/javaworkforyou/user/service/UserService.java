package pet.project.javaworkforyou.user.service;

import pet.project.javaworkforyou.user.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto saveUser(UserDto userDto);

    List<UserDto> getAllUsers(List<Long> userIds, int from, int size);

    void deleteUser(Long userId);
}
