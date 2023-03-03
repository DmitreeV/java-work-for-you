package pet.project.javaworkforyou.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pet.project.javaworkforyou.error.exception.ConflictException;
import pet.project.javaworkforyou.user.dto.UserDto;
import pet.project.javaworkforyou.user.mapper.UserMapper;
import pet.project.javaworkforyou.user.model.User;
import pet.project.javaworkforyou.user.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto saveUser(UserDto userDto) {
        User user = userMapper.toUser(userDto);
        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("User is already exists.");
        }
        log.info("Saved new user {}.", userDto);
        return userMapper.toUserDto(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> getAllUsers(List<Long> userIds, int from, int size) {
        if (userIds.isEmpty()) {
            return new ArrayList<>();
        }
        List<User> users = userRepository.findAllByIdIn(userIds, PageRequest.of(from / size, size));
        log.info("Received a list of all users with size of {}.", users.size());
        return users.stream()
                .map(userMapper::toUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Long userId) {
        log.info("User with userId {} is deleted.", userId);
        userRepository.deleteById(userId);
    }
}
