package br.com.neki.projeto.security.service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.neki.projeto.security.domain.User;
import br.com.neki.projeto.security.enums.RoleEnum;


public class UserDetailsImpl  implements UserDetails {
	
	private static final long serialVersionUID = 1L;

	private Long id;

	private String email;
	
	private Long loggedUserId;

	@JsonIgnore
	private String password;

	private Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl(Long id, String email, String password, Long loggedUserId, Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
		this.loggedUserId = loggedUserId;
	}

	public static UserDetailsImpl build(User user) {
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name())).collect(Collectors.toList());
		
		return	user.getRoles().stream().anyMatch(role -> role.getName().equals(RoleEnum.ROLE_USER)) ?
		new UserDetailsImpl(user.getId(), user.getEmail(), user.getPassword(), user.getUsuario()!= null ?user.getUsuario().getUsuarioId(): null, authorities):
			
			new UserDetailsImpl(user.getId(), user.getEmail(), user.getPassword(), user.getUsuario()!= null ?user.getUsuario().getUsuarioId(): null, authorities);
			
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}
	

	public Long getLoggedUserId() {
		return loggedUserId;
	}

	public void setLoggedUserId(Long loggedUserId) {
		this.loggedUserId = loggedUserId;
	}

	public String getPassword() {
		return password;
	}

	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(id, user.id);
	}
	
	public String getUsername() {
		return email;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
