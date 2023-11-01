package com.system.backend.Service.Implement;

import com.system.backend.Repository.TokenRepository;
import com.system.backend.Service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenImplement implements TokenService {
    @Autowired
    private TokenRepository tokenRepository;
    @Override
    public void deleteTokenByUser_id(Integer user_id) {
        tokenRepository.deleteTokenByUser_id(user_id);
    }
}
