package com.bangez.api.service;

import com.bangez.api.domain.vo.MessengerVO;
import com.bangez.api.domain.model.SellArticle;
import com.bangez.api.domain.dto.SellArticleDTO;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public interface SellArticleService {
    MessengerVO save(SellArticleDTO sellArticleDTO);
    MessengerVO deleteById(Long id);
    List<SellArticleDTO> findAll();
    SellArticle modify(Long id, SellArticle newSellArticle);
    Optional<SellArticleDTO> findById(Long id);



    default SellArticleDTO entityToDTO(SellArticle sellArticle){
        return SellArticleDTO.builder()
                .id(sellArticle.getId())
                .postTitle(sellArticle.getPostTitle())
                .postContent(sellArticle.getPostContent())
                .postDate(sellArticle.getPostDate())
                .boardHits(sellArticle.getBoardHits())
                .buildType(sellArticle.getBuildType())
                .tradeType(sellArticle.getTradeType())
                .location(sellArticle.getLocation())
                .rentPrice(sellArticle.getRentPrice())
                .monthPrice(sellArticle.getMonthPrice())
                .tradePrice(sellArticle.getTradePrice())
                .size(sellArticle.getSize())
                .roomCount(sellArticle.getRoomCount())
                .toiletCount(sellArticle.getToiletCount())
                .numberOfApt(sellArticle.getNumberOfApt())
                .acceptForUse(sellArticle.getAcceptForUse())
                .parking(sellArticle.getParking())
                .convenient(Collections.singletonList(sellArticle.getConvenient()))
                .floor(sellArticle.getFloor())
                .hopeMove(sellArticle.getHopeMove())
                .status(sellArticle.getStatus())
                .build();
    }

    default SellArticle dtoToEntity(SellArticleDTO dto) {
        return SellArticle.builder()
                .id(dto.getId())
                .postTitle(dto.getPostTitle())
                .postContent(dto.getPostContent())
                .postDate(dto.getPostDate())
                .boardHits(dto.getBoardHits())
                .buildType(dto.getBuildType())
                .tradeType(dto.getTradeType())
                .location(dto.getLocation())
                .rentPrice(dto.getRentPrice())
                .monthPrice(dto.getMonthPrice())
                .tradePrice(dto.getTradePrice())
                .size(dto.getSize())
                .roomCount(dto.getRoomCount())
                .toiletCount(dto.getToiletCount())
                .numberOfApt(dto.getNumberOfApt())
                .acceptForUse(dto.getAcceptForUse())
                .parking(dto.getParking())
                .convenient(dto.getConvenient().toString())
                .floor(dto.getFloor())
                .hopeMove(dto.getHopeMove())
                .status(dto.getStatus())
                .build();
    }


}