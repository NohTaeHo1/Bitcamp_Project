package com.bangez.api.user.service;


import com.bangez.api.common.model.MessengerVo;
import com.bangez.api.common.service.CommandService;
import com.bangez.api.common.service.QueryService;
import com.bangez.api.user.model.User;
import com.bangez.api.user.model.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService extends CommandService<UserDto>, QueryService<UserDto> {

    MessengerVo modify(UserDto user);
    List<UserDto> findUsersByName(String name);
    List<UserDto> findUsersByJob(String job);
    Optional<User> findUserByUsername(String username);

    default User dtoToEntity(UserDto dto) {
        var user = User.builder()
                .id(dto.getId())
                .phone(dto.getPhone())
                .name(dto.getName())
                .password(dto.getPassword())
                .username(dto.getUsername())
                .job(dto.getJob())
                .build();

        //boardId로 DB를 조회해서 해당 게시판에 게시된 글의 목록을 가져올 경우

        return user;

    }

    default UserDto entityToDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .name(user.getName())
                .phone(user.getPhone())
                .job(user.getJob())
                .email(user.getEmail())
                .build();

    }

    MessengerVo login(UserDto param);

    boolean existsByUsername(String username);

    Boolean logout(String accessToken);
}
