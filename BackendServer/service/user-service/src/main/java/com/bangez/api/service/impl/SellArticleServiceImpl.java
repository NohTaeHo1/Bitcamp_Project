package com.bangez.api.service.impl;

import com.bangez.api.domain.vo.MessengerVO;
import com.bangez.api.domain.model.SellArticle;
import com.bangez.api.domain.dto.SellArticleDTO;
import com.bangez.api.repository.SellArticleRepository;
import com.bangez.api.service.SellArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class SellArticleServiceImpl implements SellArticleService {

    private final SellArticleRepository repository;

    @Override
    public MessengerVO save(SellArticleDTO dto) {
        repository.save(dtoToEntity(dto));
        return MessengerVO.builder().message("성공").build();
    }

    @Override
    public MessengerVO deleteById(Long id) {
        repository.deleteById(id);
        return MessengerVO.builder().message("삭제 성공").build();
    }

    @Override
    public List<SellArticleDTO> findAll() {
        return repository.findAll().stream()
                .map(this::entityToDTO)
                .toList();
    }

    @Override
    public SellArticle modify(Long id, SellArticle newSellArticle) {
        SellArticle existingArticle = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없음"));

        // 리플렉션을 사용하여 필드를 업데이트합니다.
        updateFields(existingArticle, newSellArticle);

        return repository.save(existingArticle);
    }

    private void updateFields(SellArticle existingArticle, SellArticle newSellArticle) {
        Field[] fields = SellArticle.class.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true); // private 필드에도 접근 가능하게 설정

            try {
                Object newValue = field.get(newSellArticle);
                if (newValue != null) {
                    field.set(existingArticle, newValue);
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException("필드 접근 중 오류 발생", e);
            }
        }
    }

    @Override
    public Optional<SellArticleDTO> findById(Long id) {
        return repository.findById(id).map(this::entityToDTO);
    }
}