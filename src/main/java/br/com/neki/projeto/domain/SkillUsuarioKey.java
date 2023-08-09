package br.com.neki.projeto.domain;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
@JsonIdentityInfo( generator = ObjectIdGenerators.PropertyGenerator.class,
property = "usuariokeyId", scope = SkillUsuarioKey.class )
public class SkillUsuarioKey implements Serializable {

	private static final long serialVersionUID = -8148547866323438010L;

	@Column(name = "usuario_cd_id")
    private Long usuariokeyId;

    @Column(name = "skill_cd_id")
    private Long skillId;
    
    public SkillUsuarioKey() {
    	
    }

	public SkillUsuarioKey(Long usuariokeyId, Long skillId) {
		this.usuariokeyId = usuariokeyId;
		this.skillId = skillId;
	}

	public Long getUsuarioId() {
		return usuariokeyId;
	}

	public void setUsuarioId(Long usuariokeyId) {
		this.usuariokeyId = usuariokeyId;
	}

	public Long getSkillId() {
		return skillId;
	}

	public void setSkillId(Long skillId) {
		this.skillId = skillId;
	}
    	
    
    
}
