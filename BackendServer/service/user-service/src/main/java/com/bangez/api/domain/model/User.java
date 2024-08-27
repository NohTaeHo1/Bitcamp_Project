package com.bangez.api.domain.model;

import com.bangez.api.domain.vo.Registration;
import com.bangez.api.domain.vo.Role;
import jakarta.persistence.*;
import lombok.*;

@Getter
@ToString
@Entity(name = "User")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String password;
    private String passwordConfirm;
    private String name;
    private Long phone;
    private String email;


    private Role role;
    private String profile;

    private Registration registration;

//    @OneToMany(mappedBy = "writer", cascade = CascadeType.REMOVE)
//    private List<Article> article;

}
