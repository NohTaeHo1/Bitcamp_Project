package com.bangez.api.service.impl;


import com.bangez.api.domain.dto.LoginDTO;
import com.bangez.api.domain.dto.OAuth2UserDTO;
import com.bangez.api.domain.dto.PrincipalUserDetails;
import com.bangez.api.domain.dto.UserDTO;
import com.bangez.api.domain.model.User;
import com.bangez.api.domain.vo.ExceptionStatus;
import com.bangez.api.domain.vo.Registration;
import com.bangez.api.domain.vo.Role;
import com.bangez.api.exception.LoginException;
import com.bangez.api.repository.UserRepository;
import com.bangez.api.service.OAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OAuthServiceImpl implements OAuthService {
    private final UserRepository repository;

    @Override
    public PrincipalUserDetails login(LoginDTO dto) {
        User user = repository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new LoginException(ExceptionStatus.UNAUTHORIZED, "존재하지 않는 사용자입니다."));
        if (user.getPassword().equals(dto.getPassword())){
            return new PrincipalUserDetails(User.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .email(user.getEmail())
                    .role(Role.ROLE_USER)
                    .build(), null);
        }else{
            throw new LoginException(ExceptionStatus.INVALID_PASSWORD, "비밀번호가 일치하지 않습니다.");
        }
    }

    @Override
    public LoginDTO oauthJoin(OAuth2UserDTO dto) {
        log.info("user oauth2 파라미터: {} ", dto);
        User oauthUser = User.builder()
                .email(dto.email())
                .name(dto.name())
//                .oauthId(dto.id())
                .profile(dto.profile())
                .registration(Registration.valueOf(Registration.GOOGLE.name()))
                .build();
        if (repository.existsByEmail(oauthUser.getEmail())) {
            log.info("if existByEmail: {}", oauthUser.getEmail());
            User existOauthUpdate = repository.findByEmail(dto.email())
                    .stream()
                    .findFirst()
                    .get();
            return LoginDTO.builder()
                    .user(UserDTO.builder()
                            .id(existOauthUpdate.getId())
                            .email(existOauthUpdate.getEmail())
                            .roles(List.of(Role.ROLE_USER))
                            .build())
                    .build();
        } else {
            var newOauthSave = repository.save(oauthUser);
            log.info("else newOauthSave: {}", newOauthSave);
//            var roleSave = repository.save(RoleModel.builder().role(0).userId(newOauthSave).build());
            return LoginDTO.builder()
                    .user(UserDTO.builder()
                            .id(newOauthSave.getId())
                            .email(newOauthSave.getEmail())
                            .roles(List.of(Role.ROLE_USER))
                            .build())
                    .build();
        }
    }
}
