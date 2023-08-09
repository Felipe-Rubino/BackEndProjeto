	package br.com.neki.projeto.repository;
	
	import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.neki.projeto.domain.SkillUsuario;
import br.com.neki.projeto.domain.SkillUsuarioKey;
	
	public interface SkillUsuarioRepository  extends JpaRepository<SkillUsuario, SkillUsuarioKey> {
		List<SkillUsuario> findAllByUsuarioUsuarioId(Long usuarioId);
		
	}	
