package com.bangez.api.user.model;

import com.bangez.api.common.model.BaseEntity;
import com.bangez.api.officetel.model.OfficetelDTO;
import com.bangez.api.officetel.model.OfficetelModel;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
@ToString(exclude = { "id", "articles","token" })
public class User extends BaseEntity {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    private String name;
    private String phone;
    private String job;
    private String token;

    @OneToMany(mappedBy = "addressId", cascade = CascadeType.REMOVE)
    private List<OfficetelModel> officetels;

    // @OneToMany(mappedBy = "user")
    // private List<Order> ordersId;

    public void setPassword(String password) {
        this.password = password;
    }
}