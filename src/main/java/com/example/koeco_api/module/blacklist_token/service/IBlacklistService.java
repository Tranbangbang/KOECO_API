package com.example.koeco_api.module.blacklist_token.service;

import com.example.koeco_api.module.blacklist_token.dto.request.BlackTokenRequest;
import com.example.koeco_api.module.blacklist_token.dto.response.BlackListResponse;

public interface IBlacklistService {
    public boolean findByJwtId(String id);
    public boolean create(BlackTokenRequest request);
    public void deleteExpired();
}
