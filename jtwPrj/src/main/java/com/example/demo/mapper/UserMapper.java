package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.security.core.GrantedAuthority;

import com.example.demo.domain.User;

@Mapper
public interface UserMapper {
	//유저 읽기
	public User readUser(String username);
	
	//유저 생성
	public void createUser(User user);
	
	//권한 읽기
	public List<GrantedAuthority> readAuthorities(String username); //granted: 부여된
	
	//권한 생성
	public void createAuthority(User user);
}
