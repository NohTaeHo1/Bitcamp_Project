package com.bangez.api;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class HomeController {

    DateTimeFormatter date =DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분");

    @GetMapping("/")
    public String getHomeController() {
        return "Welcome user-server + " + date.format(LocalDateTime.now());
    }
}