package br.com.neki.projeto.dto;

import br.com.neki.projeto.security.dto.SignupRequestDTO;

public class RegistrarUsuarioDTO {
	
	private SignupRequestDTO signupRequestDTO;

	public SignupRequestDTO getSignupRequestDTO() {
		return signupRequestDTO;
	}

	public void setSignupRequestDTO(SignupRequestDTO signupRequestDTO) {
		this.signupRequestDTO = signupRequestDTO;
	}
}
