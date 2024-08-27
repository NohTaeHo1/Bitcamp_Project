package com.bangez.api.common.service;

import com.bangez.api.common.model.MessengerVo;

public interface CommandService<T> {
    MessengerVo save(T t);
    MessengerVo deleteById(Long id);
    MessengerVo modify(T t);


}
