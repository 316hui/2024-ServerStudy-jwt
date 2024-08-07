package com.example.demo.service;

import java.time.Instant;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.RefreshToken;
import com.example.demo.mapper.RefreshTokenMapper;
import com.example.demo.mapper.UserMapper;

@Service
public class RefreshTokenService {
	
	@Autowired
	RefreshTokenMapper refreshTokenMapper;
	
	@Autowired
	UserMapper userMapper;
	
	public RefreshToken createRefreshToken(String username) {
		RefreshToken refreshToken = new RefreshToken();
		refreshToken.setUser(userMapper.readUser(username));
		refreshToken.setToken(UUID.randomUUID().toString());
		refreshToken.setExpiryDate(Instant.now().plusMillis(600000));
		refreshTokenMapper.createRefreshToken(refreshToken);
		
		return refreshToken;
	}
	
	public RefreshToken findByToken(String token) {
		return refreshTokenMapper.findByToken(token);
	}
	
	public void verifyExpiration(RefreshToken token) {
		if(token.getExpiryDate().compareTo(Instant.now()) <0) { //호출한 객체가 비교대상 보다 더 이른 시각이면 음수를 반환. => 만료일이 지남
			refreshTokenMapper.deleteRefreshToken(token);
			throw new RuntimeException(token.getToken() + "Refresh token is expired. Please make a new login..!"); //없는 토큰을 호출하며 일부러 오류내기
		}
	}
}
