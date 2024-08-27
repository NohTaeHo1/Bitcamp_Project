package com.bangez.api.common.model;

import lombok.*;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Data
@Component
public class PageDTO {

    // nowPage, rowCount, pageSize, blockSize 외부주입 count, size 1 부터, number 는 0부터
    private Long pageSize;
    private Long BLOCK_SIZE;

    //항상 이 2가지 값이 필요 -> 동시에 startRow 값도 결정됨
    private Long blockNum;
    private Long pageNum;

    //3개의 값 필요
    private Long totalCount;
    private Long pageCount;
    private Long blockCount;
    //page가 가지는 값
    private Long startRow;
    private Long endRow;

    //블락이 가지는 값
    private Long startPage;
    private Long endPage;

    //무슨 의미인지 지금은 모르겠음
    private Long nextBlock;
    private Long prevBlock;

    @Builder.Default
    private Boolean existPrev = false;

    @Builder.Default
    private Boolean existNext = false;

}
