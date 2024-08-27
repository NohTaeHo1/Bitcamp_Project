package com.bangez.api.service.impl;

import com.bangez.api.domain.model.BuyArticle;
import com.bangez.api.domain.dto.BuyArticleDTO;
import com.bangez.api.repository.BuyArticleRepository;
import com.bangez.api.service.BuyArticleService;
import com.bangez.api.domain.vo.MessengerVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class BuyArticleServiceImpl implements BuyArticleService {

    private final BuyArticleRepository repository;

    @Override
    public MessengerVO save(BuyArticleDTO dto) {
        repository.save(dtoToEntity(dto));
        return MessengerVO.builder().message("성공").build();
    }

    @Override
    public MessengerVO deleteById(Long id) {
        repository.deleteById(id);
        return MessengerVO.builder().message("삭제 성공").build();
    }

    @Override
    public List<BuyArticleDTO> findAll() {
        return repository.findAll().stream().map(this::entityToDTO).toList();
    }

    @Override
    public BuyArticle modify(Long id, BuyArticle newBuyArticle) {
        BuyArticle existingArticle = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없음"));

        // 리플렉션을 사용하여 필드를 업데이트합니다.
        updateFields(existingArticle, newBuyArticle);

        return repository.save(existingArticle);
    }

    private void updateFields(BuyArticle existingArticle, BuyArticle newBuyArticle) {
        Field[] fields = BuyArticle.class.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true); // private 필드에도 접근 가능하게 설정

            try {
                Object newValue = field.get(newBuyArticle);
                if (newValue != null) {
                    field.set(existingArticle, newValue);
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException("필드 접근 중 오류 발생", e);
            }
        }
    }

    @Override
    public Optional<BuyArticleDTO> findById(Long id) {
        // 게시글 조회 시 조회수 증가
        repository.incrementBoardHits(id);
        return repository.findById(id).map(this::entityToDTO);
    }
}
