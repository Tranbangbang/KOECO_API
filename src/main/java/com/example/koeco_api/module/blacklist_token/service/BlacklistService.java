package com.example.koeco_api.module.blacklist_token.service;

import com.example.koeco_api.module.blacklist_token.dto.request.BlackTokenRequest;
import com.example.koeco_api.module.blacklist_token.dto.response.BlackListResponse;
import com.example.koeco_api.module.blacklist_token.entity.BlacklistTokenEntity;
import com.example.koeco_api.module.blacklist_token.repository.IBlacklistRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
@Transactional
public class BlacklistService implements IBlacklistService {
    @Autowired
    private IBlacklistRepository blacklistRepository;

    @Override
    public boolean findByJwtId(String id) {
        return blacklistRepository.existsAllByJwtId(id);
    }

    @Override
    public boolean create(BlackTokenRequest request) {
        BlacklistTokenEntity entity = BlacklistTokenEntity.builder()
                .jwtId(request.getJwtId())
                .expired(request.getExpired())
                .build();

        var save = blacklistRepository.save(entity);

        return true;
    }

    @Override
    public void deleteExpired() {
        try {
            Long nowtime = System.currentTimeMillis();
            blacklistRepository.deleteTokenExpired(nowtime);
        } catch (Exception e) {
            log.error("err when delete token expired: ", e.getMessage());
        }
    }

}
