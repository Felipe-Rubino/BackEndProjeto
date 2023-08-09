package br.com.neki.projeto.domain;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import br.com.neki.projeto.security.domain.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
@JsonIdentityInfo( generator = ObjectIdGenerators.PropertyGenerator.class,
property = "usuarioId", scope = Usuario.class )
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="usuario_cd_id")
	private Long usuarioId;
	
	@OneToOne(mappedBy="usuario")
	private User usuario;
	
	@OneToMany(mappedBy= "usuario")
	private Set<SkillUsuario> skillUsuario;
	
	@Column(name="usu_tx_nome")
	private String nome;
	
	public Usuario() {
		
	}

	public Usuario(Long usuarioId, User usuario, Set<SkillUsuario> skillUsuario, String nome) {
		this.usuarioId = usuarioId;
		this.usuario = usuario;
		this.skillUsuario = skillUsuario;
		this.nome = nome;
	}



	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}

	public User getUsuario() {
		return usuario;
	}

	public void setUsuario(User usuario) {
		this.usuario = usuario;
	}


	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}

	public Set<SkillUsuario> getSkillUsuario() {
		return skillUsuario;
	}

	public void setSkillUsuario(Set<SkillUsuario> skillUsuario) {
		this.skillUsuario = skillUsuario;
	}

	

	
	
	
}
