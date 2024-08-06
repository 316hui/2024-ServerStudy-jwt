package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.domain.RefreshToken;

@Mapper
public interface RefreshTokenMapper {
	public RefreshToken findByToken(String token); //토큰을 다시 찾으니 리프레시 토큰.
	
	public void createRefreshToken(RefreshToken refreshToken);
	
	public void deleteRefreshToken(RefreshToken refreshToken);
}
