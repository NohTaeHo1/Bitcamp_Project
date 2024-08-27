package com.bangez.api.common.service;


import com.bangez.api.common.model.PageDTO;

public interface PageService {
    PageDTO getPageDTO(long totalCount, int pageSize, int pageNum);
}
