package br.com.neki.projeto.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import br.com.neki.projeto.domain.Imagem;
import br.com.neki.projeto.domain.Skill;
import br.com.neki.projeto.domain.SkillUsuario;
import br.com.neki.projeto.domain.SkillUsuarioKey;
import br.com.neki.projeto.domain.Usuario;
import br.com.neki.projeto.dto.PutLevelSkillDTO;
import br.com.neki.projeto.dto.SkillDTO;
import br.com.neki.projeto.exceptions.NotFoundExeception;
import br.com.neki.projeto.repository.SkillRepository;
import br.com.neki.projeto.repository.SkillUsuarioRepository;
import br.com.neki.projeto.repository.UsuarioRepository;
import br.com.neki.projeto.security.repository.UserRepository;


@Service
public class SkillService {
	
	@Autowired
	SkillRepository skillRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	SkillUsuarioRepository skillUsuarioRepository;
	
	public List<SkillDTO> listarTudo(){
		List<Skill> skills = skillRepository.findAll();
		List<SkillDTO> skillDTO = new ArrayList<>();
		for(Skill skill : skills) {
			SkillDTO skillReturnDTO = new SkillDTO();
			skillReturnDTO.setNomeSkill(skill.getNomeSkill());
			skillReturnDTO.setDescricao(skill.getDescricao());
			skillReturnDTO.setImagem(skill.getImagem());
			skillReturnDTO.setSkillId(skill.getSkillId());
			skillDTO.add(skillReturnDTO);
		
			

		}	
		return skillDTO;
	}
	
	public SkillDTO buscarSkillId(Long id) {
		Optional<Skill> skillOptional = skillRepository.findById(id);
		Skill skill = skillOptional.get();
		SkillDTO skillReturnDTO = new SkillDTO();
		skillReturnDTO.setDescricao(skill.getDescricao());
		skillReturnDTO.setImagem(skill.getImagem());
		skillReturnDTO.setNomeSkill(skill.getNomeSkill());
		skillReturnDTO.setSkillId(skill.getSkillId());
		
		return skillReturnDTO;
	}
	
	public SkillDTO mapToResponseDTO(Skill skill) {
		SkillDTO skillDTO = new SkillDTO();
		skillDTO.setDescricao(skill.getDescricao());
		skillDTO.setImagem(skill.getImagem());
		skillDTO.setNomeSkill(skill.getNomeSkill());
		skillDTO.setSkillId(skill.getSkillId());
		return skillDTO;
	}
	
	
	public Skill mapToEntity(String nomeSkill,String descricao, MultipartFile file) throws IOException {
		Skill skill = new Skill();
		Imagem imagem = new Imagem();
		imagem.setDados(file.getBytes());
		imagem.setNome(file.getName());
		imagem.setTipo(file.getContentType());
		skill.setDescricao(descricao);
		skill.setImagem(imagem);
		skill.setNomeSkill(nomeSkill);
		return skill;
	}
	
	@Transactional
	public SkillDTO addSkill(MultipartFile file,String nomeSkill, String descricao) throws IOException {
		Skill skill = mapToEntity(nomeSkill, descricao, file);
		Skill skillSalvo = skillRepository.save(skill);
		SkillDTO returnSkillDTO = mapToResponseDTO(skillSalvo);
		return returnSkillDTO;
	}
	
	public void excluirSkill(Long id) {
		skillRepository.deleteById(id);
	}
	
	@Transactional
	public void excluirSkillUsuario(Long skillId, Long usuarioId) {
		SkillUsuarioKey skillUsuarioKey = new SkillUsuarioKey(usuarioId,skillId);
		Optional<SkillUsuario> skUsuario = skillUsuarioRepository.findById(skillUsuarioKey);
		if(skUsuario.isEmpty()) {
			System.out.println("Não foi encontrado");
			return;
		}
		
		skillUsuarioRepository.delete(skUsuario.get());
	}
	
	public String relacionarUsuario(PutLevelSkillDTO dto) {
		Optional<Skill> skillOPT = skillRepository.findById(dto.getSkillId());
		Optional<Usuario> usuarioOPT = usuarioRepository.findById(dto.getUsuarioId());
		if(usuarioOPT.isEmpty()) {
			return "Usuario nao encontrado";
		}
		if(skillOPT.isEmpty()) {
			return "skill nao encontrado";
		}
		Skill skill = skillOPT.get();
		Usuario usuario = usuarioOPT.get();
		SkillUsuarioKey skillUsuarioKey = new SkillUsuarioKey();
		skillUsuarioKey.setSkillId(dto.getSkillId());
		skillUsuarioKey.setUsuarioId(dto.getUsuarioId());
		SkillUsuario skillUsuario = new SkillUsuario();
		skillUsuario.setSkill(skill);
		skillUsuario.setId(skillUsuarioKey);
		skillUsuario.setUsuario(usuario);
		skillUsuario.setLevelSkill(dto.getLevelSkill());
		skillUsuarioRepository.save(skillUsuario);
		return "Relacionado com sucesso a skill " + skill.getSkillId() + " Com o usuario " + usuarioOPT.get().getUsuarioId();
	}
	
	@Transactional
	public List<SkillDTO> findAllBySkill(Long usuarioId){
		List<SkillUsuario> su = skillUsuarioRepository.findAllByUsuarioUsuarioId(usuarioId);
		List<SkillDTO> skillReturnDTO = new ArrayList<>();
		for (SkillUsuario skillUsuario : su ) {
				Skill skill = skillUsuario.getSkill();
				SkillDTO skillDTO = new SkillDTO();
				skillDTO.setLevelSkill(skillUsuario.getLevelSkill());
				skillDTO.setDescricao(skill.getDescricao());
				skillDTO.setImagem(skill.getImagem());
				skillDTO.setNomeSkill(skill.getNomeSkill());
				skillDTO.setSkillId(skill.getSkillId());
				skillReturnDTO.add(skillDTO);
		}
		return skillReturnDTO;
	}
	
	
	public void atualizarLevelSkill(PutLevelSkillDTO dtoPut) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(dtoPut.getUsuarioId());

        if (usuarioOptional.isEmpty()) {
            throw new NotFoundExeception("Não há um usuario cadastrado no ID: " + dtoPut);
        }
        SkillUsuarioKey usk = new SkillUsuarioKey();
       	usk.setSkillId(dtoPut.getSkillId());
       	usk.setUsuarioId(dtoPut.getUsuarioId());
        var usuarioSkillOptional = skillUsuarioRepository.findById(usk);
        if (usuarioSkillOptional.isEmpty()) {
            throw new NotFoundExeception("Não há uma skill no ID: " + dtoPut);
        }
        Usuario usuario = usuarioOptional.get();
        SkillUsuario skill = usuarioSkillOptional.get();
             
        skill.setLevelSkill(dtoPut.getLevelSkill());
        skillUsuarioRepository.save(skill);
    }
	
}
