package br.com.neki.projeto.security.dto;

import java.util.List;

public class JwtResponseDTO {
	
	private String token;
	private String type = "Bearer";
	private Long id;
	private String email;
	private List<String> roles;
	private Long loggedUserId;
	
	public JwtResponseDTO(String token, Long id, String email, List<String> roles, Long loggedUserId) {
		this.token = token;
		this.id = id;
		this.email = email;
		this.roles = roles;
		this.loggedUserId = loggedUserId;
	}
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	public Long getLoggedUserId() {
		return loggedUserId;
	}
	public void setLoggedUserId(Long loggedUserId) {
		this.loggedUserId = loggedUserId;
	}
	
	
	
	
}	
