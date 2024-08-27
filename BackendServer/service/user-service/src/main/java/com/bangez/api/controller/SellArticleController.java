package com.bangez.api.controller;

import com.bangez.api.domain.vo.MessengerVO;
import com.bangez.api.domain.model.SellArticle;
import com.bangez.api.domain.dto.SellArticleDTO;
import com.bangez.api.service.impl.SellArticleServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/sell-article")
@RequiredArgsConstructor
@Slf4j
public class SellArticleController {

    private final SellArticleServiceImpl service;

    @PostMapping("/save")
    public ResponseEntity<MessengerVO> save(@RequestBody SellArticleDTO dto) {
        log.info("입력받은 정보 : {}", dto);
        return ResponseEntity.ok(service.save(dto));
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<MessengerVO> deleteById(@RequestParam Long id)  {
        log.info("입력받은 정보 : {}", id );
        return ResponseEntity.ok(service.deleteById(id));
    }

    @GetMapping(path = "/list")
    public ResponseEntity<List<SellArticleDTO>> findAll( ) {
        return ResponseEntity.ok(service.findAll());
    }

    @PatchMapping(path = "/update/{id}")
    public ResponseEntity<SellArticle> modify(@PathVariable Long id, @RequestBody SellArticle newSellArticle){
        log.info("입력받은 정보 : {}", newSellArticle);
        return ResponseEntity.ok(service.modify(id, newSellArticle));
    }
    @GetMapping(path = "/detail")
    public ResponseEntity<Optional<SellArticleDTO>> findById(@RequestParam Long id) {
        log.info("입력받은 정보 : {}", id );
        return ResponseEntity.ok(service.findById(id));
    }


}