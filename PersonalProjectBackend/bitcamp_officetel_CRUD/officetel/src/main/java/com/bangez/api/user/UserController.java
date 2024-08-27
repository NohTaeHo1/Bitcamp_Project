package com.bangez.api.user;


import com.bangez.api.common.model.MessengerVo;
import com.bangez.api.user.model.UserDto;
import com.bangez.api.user.service.UserServiceImpl;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
        @ApiResponse(responseCode = "404", description = "Customer not found")})
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/users")
@Slf4j
public class UserController {
    private final UserServiceImpl service;

    @SuppressWarnings("static-access")
    @PostMapping( "/save")
    public ResponseEntity<MessengerVo> save(@RequestBody UserDto dto) {
        log.info("입력받은 정보 : {}", dto );
        return ResponseEntity.ok(service.save(dto));
    }

    @GetMapping("/list")
    public ResponseEntity<List<UserDto>> findAll(){
        log.info("입력받은 정보 : {}" );
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/detail")
    public ResponseEntity<Optional<UserDto>> findById(@RequestParam Long id) {
        log.info("입력받은 정보 : {}", id );
        return ResponseEntity.ok(service.findById(id));
    }

    @PatchMapping("/modify")
    public ResponseEntity<MessengerVo> modify(@RequestBody UserDto param) {
        log.info("입력받은 정보 : {}", param );
        return ResponseEntity.ok(service.modify(param));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<MessengerVo> deleteById(@RequestParam Long id) {
        log.info("입력받은 정보 : {}", id );
        return ResponseEntity.ok(service.deleteById(id));
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return ResponseEntity.ok(service.count());

    }

    @PostMapping("/search")
    public ResponseEntity<List<UserDto>> findUsersByName(@RequestBody UserDto param) {
        //log.info("입력받은 정보 : {}", name );
        return ResponseEntity.ok(service.findUsersByName(param.getName()));
    }

    @PostMapping(path = "/login")
    public ResponseEntity<MessengerVo> login(@RequestBody UserDto param) {
        log.info("입력받은 정보 : {}", param );
        return ResponseEntity.ok(service.login(param));
    }

    @GetMapping(path = "/exist-username")
    public ResponseEntity<Boolean> existByUsername(@RequestParam("username") String username){
        log.info("existByUsername : "+username);
        return ResponseEntity.ok(service.existsByUsername(username));
    }

    @GetMapping("/logout")
    public ResponseEntity<Boolean> logout(@RequestHeader("Authorization") String accessToken) {
        log.info("logout request : {}", accessToken);
        var flag = service.logout(accessToken);
        return ResponseEntity.ok(flag);
    }
}