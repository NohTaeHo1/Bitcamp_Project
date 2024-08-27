package com.bangez.analysis.acess_log.service.serviceImpl;

import com.bangez.analysis.acess_log.model.AccessLog;
import com.bangez.analysis.acess_log.repository.AccessLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class AccessLogServiceImpl {

    private final AccessLogRepository repository;

    public Mono<Void> recordPageVisit() {
        AccessLog log = new AccessLog();
        log.setAccessTime(LocalDateTime.now());
        return repository.save(log).then(); // 로그를 저장한 후 완료
    }

    public Mono<Map<String, Long>> getTodayAccessCount() {
        LocalDateTime todayStart = LocalDateTime.now().toLocalDate().atStartOfDay();
        return repository.findByAccessTimeAfter(todayStart)
                .count()  // Count the number of logs
                .map(count -> {
                    Map<String, Long> result = new HashMap<>();
                    result.put("count", count);
                    return result;
                });
    }


}
