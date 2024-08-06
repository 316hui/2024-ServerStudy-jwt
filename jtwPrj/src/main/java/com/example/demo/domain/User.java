package com.example.demo.domain;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class User implements UserDetails{
	private static final long serialVersionUID = 1L;
	
	private String username; //u_id
	private String password; //u_password
	private String uName;	//u_name
	private String uDateTime;	//u_datetime
	
	//security 관련
	private Collection<? extends GrantedAuthority> authorities;
	
	private boolean isAccountNonExpired;
	private boolean isAccountNonLocked;
	private boolean isCredentialsNonExpired; //자격증명..
	private boolean isEnabled;
	
	
	
	
	public String getuName() {
		return uName;
	}
	
	public void setuName(String uName) {
		this.uName = uName;
	}
	
	public String getuDateTime() {
		return uDateTime;
	}
	
	public void setuDateTime(String uDateTime) {
		this.uDateTime = uDateTime;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setAccountNonExpired(boolean isAccountNonExpired) {
		this.isAccountNonExpired = isAccountNonExpired;
	}
	
	public void setAccountNonLocked(boolean isAccountNonLocked) {
		this.isAccountNonExpired = isAccountNonLocked;
	}
	
	public void setCredentialsNonExpired(boolean isCredentialsNonExpired) {
		this.isCredentialsNonExpired = isCredentialsNonExpired;
	}
	
	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
	
	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	//----------------------------------------------------------------------
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return UserDetails.super.isAccountNonExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return UserDetails.super.isAccountNonLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return UserDetails.super.isCredentialsNonExpired();
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return UserDetails.super.isEnabled();
	}
	
	 @Override
	    public String toString() {
	        return "User [username=" + username + ", password=" + password + ", uName=" + uName + ", uDateTime=" + uDateTime
	                + ", authorities=" + authorities + ", isAccountNonExpired=" + isAccountNonExpired
	                + ", isAccountNonLocked=" + isAccountNonLocked + ", isCredentialsNonExpired=" + isCredentialsNonExpired
	                + ", isEnabled=" + isEnabled + "]";
	}
	
	
}
