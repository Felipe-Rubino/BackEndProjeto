package br.com.neki.projeto.dto;

public class PutLevelSkillDTO {
	
	private Long skillId;
	
	private Integer levelSkill;
	
	private Long usuarioId;

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

	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}
	
	
}
