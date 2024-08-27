package com.bangez.api.officetel.service;

import com.bangez.api.common.model.MessengerVo;
import com.bangez.api.officetel.model.OfficetelDTO;
import com.bangez.api.officetel.repository.OfficetelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class OfficetelServiceImpl implements OfficetelService {

    private final OfficetelRepository repository;

    @Override
    public Long countOfficetel() {
        return repository.count();
    }

    @Override
    public List<OfficetelDTO> findAll() {
        return repository.findAll().stream().map(this::entityToDto).toList();
    }

    @Override
    public Optional<OfficetelDTO> findById(Long id){
        return repository.findById(id).map(this::entityToDto);
    }

    @Override
    public MessengerVo insert(OfficetelDTO dto){
        Long pre = countOfficetel();
        repository.insert(dtoToEntity(dto));
        Long after = countOfficetel();
        return MessengerVo.builder()
                .message(after == pre + 1?"SUCCESS":"FAILURE")
                .build();
    }

    @Override
    public MessengerVo modify(OfficetelDTO dto){
        OfficetelDTO officetelDto = findById(dto.getId()).orElseThrow(() -> new IllegalArgumentException("dto가 존재하지 않음"));

        repository.modify(dtoToEntity(officetelDto));
        return MessengerVo.builder()
                .message(repository.findById(dto.getId()).orElseThrow(() -> new IllegalArgumentException("해당 ID유저가 존재하지 않음")).equals(dtoToEntity(officetelDto))?"SUCCESS":"FAILURE")
                .build();
    }

    @Override
    public MessengerVo removebyId(Long id){
        repository.removebyId(id);
        return MessengerVo.builder()
                .message(repository.findById(id).isEmpty()?"SUCCESS":"FAILURE")
                .build();
    }



}
