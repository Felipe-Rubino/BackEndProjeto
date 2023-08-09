package br.com.neki.projeto.domain;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name= "skill")
@JsonIdentityInfo( generator = ObjectIdGenerators.PropertyGenerator.class,
property = "skillId", scope = Skill.class )
public class Skill {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="skill_cd_id")
	private Long skillId;
	
	@Column(name="skill_tx_nome")
	private String nomeSkill;
	
	@Column(name="skill_tx_descricao")
	private String descricao;
	
	@OneToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name = "fk_imagem_id")
	private Imagem imagem;
	
	@OneToMany(mappedBy="skill", cascade=CascadeType.ALL)
	@JsonIgnore
	private Set<SkillUsuario> skillUsuario;
	
	public Skill() {
		
	}
	public Skill(Long skillId, String nomeSkill, String descricao, Imagem imagem, Set<SkillUsuario> skillUsuario) {
		this.skillId = skillId;
		this.nomeSkill = nomeSkill;
		this.descricao = descricao;
		this.imagem = imagem;
		this.skillUsuario = skillUsuario;
		
	}


	public Long getSkillId() {
		return skillId;
	}

	public void setSkillId(Long skillId) {
		this.skillId = skillId;
	}

	public String getNomeSkill() {
		return nomeSkill;
	}

	public void setNomeSkill(String nomeSkill) {
		this.nomeSkill = nomeSkill;
	}

	

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Imagem getImagem() {
		return imagem;
	}

	public void setImagem(Imagem imagem) {
		this.imagem = imagem;
	}
	public Set<SkillUsuario> getSkillUsuario() {
		return skillUsuario;
	}
	public void setSkillUsuario(Set<SkillUsuario> skillUsuario) {
		this.skillUsuario = skillUsuario;
	}
	
	

	
	


	

	
	

	
	
	
}
