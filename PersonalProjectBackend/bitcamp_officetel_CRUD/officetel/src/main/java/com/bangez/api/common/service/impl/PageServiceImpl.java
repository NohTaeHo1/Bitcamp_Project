package com.bangez.api.common.service.impl;

import com.bangez.api.common.model.PageDTO;
import com.bangez.api.common.service.PageService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PageServiceImpl implements PageService {

    public PageDTO getPageDTO(long totalCount, int pageSize, int pageNum) {

        final long BLOCK_SIZE = 10;
        long blockNum = (pageNum - 1) / BLOCK_SIZE + 1;

        long pageCount = (totalCount % pageSize == 0) ? totalCount / pageSize : totalCount / pageSize + 1;
        long blockCount = (pageCount % BLOCK_SIZE == 0) ? pageCount / BLOCK_SIZE : pageCount / BLOCK_SIZE + 1;

        long startRow = (pageNum - 1) * pageSize;
        long endRow = (pageNum * pageSize > totalCount) ? totalCount : pageNum * pageSize;

        long startPage = (blockNum - 1) * BLOCK_SIZE + 1;
        long endPage = (blockNum * BLOCK_SIZE > pageCount) ? pageCount : blockNum * BLOCK_SIZE;

        boolean existPrev = blockNum > 1;
        boolean existNext = blockNum < blockCount;

        long prevBlock = blockNum - 1;
        long nextBlock = blockNum + 1;

        return PageDTO.builder()
                .totalCount(totalCount)
                .pageCount(pageCount)
                .blockCount(blockCount)
                .startRow(startRow)
                .endRow(endRow)
                .startPage(startPage)
                .endPage(endPage)
                .existPrev(existPrev)
                .existNext(existNext)
                .prevBlock(prevBlock)
                .nextBlock(nextBlock)
                .build();
    }

    List<String> a = new ArrayList<>();
}