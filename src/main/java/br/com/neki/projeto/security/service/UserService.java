package br.com.neki.projeto.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.neki.projeto.security.domain.User;
import br.com.neki.projeto.security.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;

	public Optional<User> findByEmail(String email) {
	    return userRepository.findUserByEmail(email);
	}
}
