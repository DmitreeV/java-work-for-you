package pet.project.javaworkforyou.user.mapper;

import org.mapstruct.Mapper;
import pet.project.javaworkforyou.user.dto.UserDto;
import pet.project.javaworkforyou.user.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(UserDto userDto);

    UserDto toUserDto(User user);
}
