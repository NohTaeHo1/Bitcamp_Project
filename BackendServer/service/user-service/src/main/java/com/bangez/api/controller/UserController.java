package com.bangez.api.controller;

import com.bangez.api.domain.vo.MessengerVO;
import com.bangez.api.domain.dto.UserDTO;
import com.bangez.api.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserServiceImpl service;

    @PostMapping("/add")
    public ResponseEntity<MessengerVO> save(@RequestBody UserDTO dto){
        log.info("입력받은 정보 : {}", dto);
        return ResponseEntity.ok(service.save(dto));
    }

    @GetMapping("/detail/{userId}")
    public ResponseEntity<UserDTO> getDetail(@PathVariable("userId") Long userId){
        return ResponseEntity.ok(service.getDetail(userId));
    }

}