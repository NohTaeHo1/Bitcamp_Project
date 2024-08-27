package com.bangez.api.common.model;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class) // mod_date 설정.
@Getter
public class BaseEntity {
    //article postdate 삭제했음
    @CreatedDate
    @Column(name = "post_date", updatable = false)
    private LocalDateTime postdate;

    //데이터 생성, 수정 날짜를 모두 기록
    @LastModifiedDate
    @Column(name = "mod_date")
    private LocalDateTime modDate;
}
