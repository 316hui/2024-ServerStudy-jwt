package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.AuthRequestDTO;
import com.example.demo.domain.JwtResponseDTO;
import com.example.demo.domain.RefreshToken;
import com.example.demo.domain.RefreshTokenRequestDTO;
import com.example.demo.domain.User;
import com.example.demo.service.JwtService;
import com.example.demo.service.RefreshTokenService;
import com.example.demo.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	JwtService jwtService;
	@Autowired
	RefreshTokenService refreshTokenService;
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	UserService userservice;
	
	@GetMapping("/hello")
	public User greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new User();
	}
	
	/*@PostMapping("/api/login")
	 * public JwtResponseDTO AUthenticateAndGetToken(@RequestBody AuthRequestDTO authRequestDTO) {
	 * 		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword()));
	 * 		if(authentication.isAuthenticated()) {
	 * 			JwtResponseDTO jwtResponseDTO = new JwtResponseDTO();
	 * 			jwtResponseDTo.setAccessToken(jwtService.GenerateToken(authRequestDTO.getUsername()));
	 * 			return jwtResponseDTO;
	 * 
	 * 		} else {
	 * 			throw new UsernameNotFoundException("invalid user request...!");
	 * }
	 * }
	 
	 */
	
	@PostMapping("/api/login")
	public JwtResponseDTO AuthenticateAndGetToken(@RequestBody AuthRequestDTO authRequestDTO) {
		 Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword()));	
		 if(authentication.isAuthenticated()) {
			//RefreshToken refreshTOken = refreshTokenService.createRefreshToken(authRequestDTO.getUsername());
			JwtResponseDTO jwtResponseDTO = new JwtResponseDTO();
			jwtResponseDTO.setAccessToken(jwtService.GenerateToken(authRequestDTO.getUsername()));
			//jwtResponseDTO.setToken(refreshTOken.getToken());
			return jwtResponseDTO;
		} else {
			throw new UsernameNotFoundException("invalid user request...!!!");
		}
	}
	
	
	 @PostMapping("/api/refreshToken")
	    public JwtResponseDTO refreshToken(@RequestBody RefreshTokenRequestDTO refreshTokenRequestDTO){
	        RefreshToken refreshToken = refreshTokenService.findByToken(refreshTokenRequestDTO.getToken());
	        refreshTokenService.verifyExpiration(refreshToken);
	        User user = refreshToken.getUser();
	        if (user == null) {
	            throw new RuntimeException("Refresh Token is not in DB..!!");
	        }
	        String accessToken = jwtService.GenerateToken(user.getUsername());

	        JwtResponseDTO jwtResponseDTO = new JwtResponseDTO();
	        jwtResponseDTO.setAccessToken(accessToken);
	        jwtResponseDTO.setToken(refreshTokenRequestDTO.getToken());

	        return jwtResponseDTO;
		
		/*return refreshTokenService.findByToken(refreshTokenRequestDTO.getToken())
        .map(refreshTokenService::verifyExpiration)
        .map(RefreshToken::getUserInfo)
        .map(userInfo -> {
            String accessToken = jwtService.GenerateToken(userInfo.getUsername());
            return JwtResponseDTO.builder()
                    .accessToken(accessToken)
                    .token(refreshTokenRequestDTO.getToken()).build();
        }).orElseThrow(() ->new RuntimeException("Refresh Token is not in DB..!!"));*/
	 }
	 
	 @GetMapping("/api/users/me")
	 public User getUsersMe(@AuthenticationPrincipal User user) {
		 return user;
	 }
	 
	 @PreAuthorize("hasAuthority('ROLE_ADMIN')")
	 @GetMapping("/ping")
	 public String test() {
		 try {
			 return "Welcome";
		 } catch(Exception e) {
			 throw new RuntimeException(e);
		 }
	 }
}


