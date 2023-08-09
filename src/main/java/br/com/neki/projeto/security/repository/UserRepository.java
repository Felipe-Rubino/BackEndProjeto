package br.com.neki.projeto.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.neki.projeto.security.domain.User;

@Repository("user")
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findUserByEmail(String email);
	
	Optional<User> findUserById (Long id);
	
	Boolean existsByEmail(String email);

}