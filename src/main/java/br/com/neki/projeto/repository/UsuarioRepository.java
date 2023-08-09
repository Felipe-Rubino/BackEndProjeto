package br.com.neki.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.neki.projeto.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
//	Usuario findByLongId(Long usuarioId);
}
