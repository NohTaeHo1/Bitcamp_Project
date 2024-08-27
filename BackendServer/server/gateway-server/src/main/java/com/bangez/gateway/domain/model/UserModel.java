package com.bangez.gateway.domain.model;

import lombok.*;
import com.bangez.gateway.domain.vo.Role;

import java.util.List;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
    private Long id;
    private String email;
    private String name;
    private List<Role> roles;
    private String registration;
}
