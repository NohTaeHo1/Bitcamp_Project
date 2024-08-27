package com.bangez.api.domain.dto;

import com.bangez.api.domain.vo.Registration;
import com.bangez.api.domain.vo.Role;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Component
@ToString
public class UserDTO {
    private Long id;
    private String userName;
    private String password;
    private String passwordConfirm;
    private String name;
    private Long phone;
    private String email;

    private List<Role> roles;
    private String profile;

    private Registration registration;
}
