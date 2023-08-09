	package br.com.neki.projeto.dto;

import br.com.neki.projeto.domain.Imagem;

public class SkillDTO {
	
	private Long skillId;
	private String nomeSkill;
	private String descricao;
	private Imagem imagem;
	private Integer levelSkill;
	
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
	public Long getSkillId() {
		return skillId;
	}
	public void setSkillId(Long skillId) {
		this.skillId = skillId;
	}
	public Integer getLevelSkill() {
		return levelSkill;
	}
	public void setLevelSkill(Integer levelSkill) {
		this.levelSkill = levelSkill;
	}
	
	

	
	
}
