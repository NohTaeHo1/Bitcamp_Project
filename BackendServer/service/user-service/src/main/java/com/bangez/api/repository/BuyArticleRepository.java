package com.bangez.api.repository;

import com.bangez.api.domain.model.BuyArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface BuyArticleRepository extends JpaRepository<BuyArticle, Long> {
    @Modifying
    @Query("UPDATE buyArticles b SET b.boardHits = b.boardHits + 1 WHERE b.id = :id")
    void incrementBoardHits(Long id);
}