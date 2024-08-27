package com.bangez.api.service.impl;

import com.bangez.api.domain.vo.MessengerVO;
import com.bangez.api.domain.dto.UserDTO;
import com.bangez.api.repository.UserRepository;
import com.bangez.api.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Override
    public MessengerVO save(UserDTO t){
        entityToDTO((repository.save(dtoToEntity(t))));
        return MessengerVO.builder().message("True").build();
    }

    @Override
    public UserDTO getDetail(Long userId) {
        return repository.findById(userId)
                .map(this::entityToDTO)
                .orElse(null);
    }
}