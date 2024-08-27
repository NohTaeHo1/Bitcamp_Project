package com.bangez.api.repository;

import com.bangez.api.domain.model.SellArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellArticleRepository extends JpaRepository<SellArticle, Long> {
}