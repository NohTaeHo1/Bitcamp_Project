package com.bangez.api.user.service;

import com.bangez.api.common.component.security.JwtProvider;
import com.bangez.api.common.model.MessengerVo;
import com.bangez.api.user.model.User;
import com.bangez.api.user.model.UserDto;
import com.bangez.api.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SuppressWarnings("rawtypes")
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repo;

    private final JwtProvider jwtProvider;

    @Override
    public MessengerVo save(UserDto dto) {
        return MessengerVo.builder()
                .message((repo.save(dtoToEntity(dto)) instanceof User)?"SUCESS":"FAILURE")
                .build();
    }

    @Override
    public MessengerVo modify(UserDto dto) {
        return MessengerVo.builder()
                .message((repo.save(dtoToEntity(dto)) instanceof User)?"SUCESS":"FAILURE")
                .build();
    }

    @Override
    public MessengerVo deleteById(Long id) {
        repo.deleteById(id);
        return MessengerVo.builder()
                .message((repo.findById(id).isEmpty())?"SUCCESS":"FAILURE")
                .build();
//        return MessengerVo.builder()
//                .message(
//                        Stream.of(id)
//                                .filter(i -> existsById(i))
//                                .peek(i -> repo.deleteById(i))
//                                .map(i -> "SUCCESS")
//                                .findAny()
//                                .orElseGet(() -> "FAILURE")
//                ).build();
    }

    @Override
    public List<UserDto> findAll() {
        return repo.findAllByOrderByIdDesc().stream().map(i -> entityToDto(i)).toList();
    }

    @Override
    public Optional<UserDto> findById(Long id) {
        return repo.findById(id).map(i -> entityToDto(i));
    }

    @Override
    public long count() {
        return repo.count();
    }

    @Override
    public boolean existsById(Long id) {
        return repo.existsById(id);
    }

    @Override
    public List<UserDto> findUsersByName(String name) {
        return repo.findAll().stream()
                .filter(user -> user.getName().equals(name))
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDto> findUsersByJob(String job) {

        return repo.findAll().stream()
                .filter(i -> i.getJob().equals(job))
                .map(i -> entityToDto(i))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        return repo.findByUsername(username);
    }

    @Transactional
    @Override
    public MessengerVo login(UserDto dto) {
        var user = repo.findByUsername(dto.getUsername()).get();
        var accessToken = jwtProvider.createToken(entityToDto(user));
        var flag = user.getPassword().equals(dto.getPassword());
        //토큰을 각 섹션(Header, Payload, Signature)으로 분할
        jwtProvider.printPayload(accessToken);
        repo.modifyUserById(accessToken, user.getId());

        return MessengerVo.builder()
                .message(flag ? "SUCCESS" : "FAILURE")
                .accessToken(flag ? accessToken : "None")
                .build();
    }

//    @Override
//    public MessengerVo login(UserDto dto) {
//        return MessengerVo.builder()
//                .message(findUserByUsername(dto.getUsername()).get().getPassword().equals(dto.getPassword())? "SUCCESS": "FAILURE")
//                .token(flag? jwtProvider.createToken(dto) :"None" )
//                .build();
//    }
//    @Override
//    public MessengerVo login(UserDto param) {
//        return MessengerVo.builder()
//                .message(repo.findAll().stream()
//                        .filter(i->i.getUsername().equals(param.getUsername()))
//                        .filter(i->i.getPassword().equals(param.getPassword()))
//                        .map(i->"성공")
//                        .findAny().orElse("실패"))
//                .build();
//    }

    @Override
    public boolean existsByUsername(String username) {
        int count = repo.getCount(username);
        return (count == 1) ? true : false;
        //(count == 1) ? true : false; 여기서 뒤에 true:false; 생략가능
    }

    @Transactional
    @Override
    public Boolean logout(String token) {
        String deleteToken = "";
        //log.info("token : "+token);
        String accessToken = token.substring(7);

        Long id = repo.findUserByToken(accessToken).getId();
        log.info("id : "+id);
        repo.modifyUserById(deleteToken, id);
        log.info("repo.findById(id).get().getToken().isEmpty() : {}", repo.findById(id).get().getToken().isEmpty());

        return repo.findById(id).get().getToken().isEmpty();
    }
}
