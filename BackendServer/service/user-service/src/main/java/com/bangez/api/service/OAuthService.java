package com.bangez.api.service;

import com.bangez.api.domain.dto.LoginDTO;
import com.bangez.api.domain.dto.OAuth2UserDTO;
import com.bangez.api.domain.dto.PrincipalUserDetails;

public interface OAuthService {

    PrincipalUserDetails login(LoginDTO dto);


    LoginDTO oauthJoin(OAuth2UserDTO dto);

}
