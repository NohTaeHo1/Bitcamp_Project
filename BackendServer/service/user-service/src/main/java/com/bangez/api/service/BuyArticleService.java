package com.bangez.api.service;

import com.bangez.api.domain.model.BuyArticle;
import com.bangez.api.domain.dto.BuyArticleDTO;
import com.bangez.api.domain.vo.MessengerVO;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public interface BuyArticleService {
    MessengerVO save(BuyArticleDTO buyArticleDTO);
    MessengerVO deleteById(Long id);
    List<BuyArticleDTO> findAll();
    BuyArticle modify(Long id, BuyArticle newBuyArticle);
    Optional<BuyArticleDTO> findById(Long id);



    default BuyArticleDTO entityToDTO(BuyArticle buyArticle){
        return BuyArticleDTO.builder()
                .id(buyArticle.getId())
                .postTitle(buyArticle.getPostTitle())
                .postContent(buyArticle.getPostContent())
                .postDate(buyArticle.getPostDate())
                .boardHits(buyArticle.getBoardHits())
                .buildType(buyArticle.getBuildType())
                .tradeType(buyArticle.getTradeType())
                .location(buyArticle.getLocation())
                .rentPrice(buyArticle.getRentPrice())
                .monthPrice(buyArticle.getMonthPrice())
                .tradePrice(buyArticle.getTradePrice())
                .size(buyArticle.getSize())
                .roomCount(buyArticle.getRoomCount())
                .toiletCount(buyArticle.getToiletCount())
                .numberOfApt(buyArticle.getNumberOfApt())
                .acceptForUse(buyArticle.getAcceptForUse())
                .parking(buyArticle.getParking())
                .convenient(Collections.singletonList(buyArticle.getConvenient()))
                .floor(buyArticle.getFloor())
                .hopeMove(buyArticle.getHopeMove())
                .moreContent(buyArticle.getMoreContent())
                .status(buyArticle.getStatus())
                .build();
    }

    default BuyArticle dtoToEntity(BuyArticleDTO dto) {
        return BuyArticle.builder()
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
                .moreContent(dto.getMoreContent())
                .status(dto.getStatus())
                .build();
    }


}