package br.com.neki.projeto.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "skill_usuario")
@JsonIdentityInfo( generator = ObjectIdGenerators.PropertyGenerator.class,
property = "skillusuarioId", scope = SkillUsuario.class )
public class SkillUsuario {
	@EmbeddedId
	private SkillUsuarioKey id;
	
	@ManyToOne
	@MapsId("usuarioId")
	@JoinColumn(name="usuario_cd_id")
	private Usuario usuario;
	
	@ManyToOne
	@MapsId("skillId")
	@JoinColumn(name="skill_cd_id")
	private Skill skill;
	
	@Column(name="skillusu_nm_level")
	private Integer levelSkill;
	
	public SkillUsuario() {
		
	}

	public SkillUsuario(SkillUsuarioKey id, Usuario usuario, Skill skill, Integer levelSkill) {
		
		this.id = id;
		this.usuario = usuario;
		this.skill = skill;
		this.levelSkill = levelSkill;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

	public Integer getLevelSkill() {
		return levelSkill;
	}

	public void setLevelSkill(Integer levelSkill) {
		this.levelSkill = levelSkill;
	}
	
	public SkillUsuarioKey getId() {
		return id;
	}

	public void setId(SkillUsuarioKey id) {
		this.id = id;
	}

	
}
