package com.bangez.api.domain.dto;

import com.bangez.api.domain.vo.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {
    private String email;
    private String password;
    private List<Role> role;
    private UserDTO user;
}

