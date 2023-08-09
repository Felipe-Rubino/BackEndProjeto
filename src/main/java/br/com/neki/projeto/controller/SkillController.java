package br.com.neki.projeto.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.neki.projeto.dto.PutLevelSkillDTO;
import br.com.neki.projeto.dto.SkillDTO;
import br.com.neki.projeto.service.SkillService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/skill")
public class SkillController {
	
	@Autowired
	SkillService skillService;
	
	@GetMapping("/listar")
	@Operation(summary = "Lista todas as skills", description = "Listagem de skills")
	public List<SkillDTO> listar(){
		return skillService.listarTudo();
	}
	
	@PostMapping(path = "/inserir", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	@Operation(summary = "Cadastro de skill", description = "Cadastro")
	public ResponseEntity<Object> addCargo(@RequestParam("imagem") MultipartFile file,String nomeSkill, String descricao) throws IOException {
		var skillSalva = skillService.addSkill(file, nomeSkill, descricao);
		return ResponseEntity.ok(skillSalva);
	}
	
	@GetMapping("/{id}")
	@Operation(summary = "Achar skill por ID", description = "Listagem de skills")
	public SkillDTO findById(@PathVariable("id") Long id) {
		return skillService.buscarSkillId(id);
	}
	
	@DeleteMapping("/{id}")
	@Operation(summary = "Deletar skill por ID", description = "Deletar skills")
	public void deletarImagem(@PathVariable("id") Long id) {
		skillService.excluirSkill(id);
	}
	
	@PutMapping("/levelSkill")
	@Operation(summary = "Relacionar skill com usuario", description = "Relação usuario e skill")
	public ResponseEntity<String> relacionarUsuario(@RequestBody PutLevelSkillDTO dto){
		return ResponseEntity.ok(skillService.relacionarUsuario(dto));
	}
	
	@GetMapping("/{id}/skillUsuario")
	@Operation(summary = "Achar todas as skills de um usuario", description = "Listagem de skills")
	public List<SkillDTO> findAllBySkill(@PathVariable("id") Long usuarioId){
		return skillService.findAllBySkill(usuarioId);
	}
	
	@PutMapping("/atualizar")
	@Operation(summary = "Atualizar level da skill", description = "Atualizar level")
    public ResponseEntity<String> atualizarNivelSkill(@RequestBody PutLevelSkillDTO dtoPut) {
        skillService.atualizarLevelSkill(dtoPut);
        return ResponseEntity.ok("Nível de habilidade da skill atualizado " + dtoPut);
    }
	
	@DeleteMapping("/{usuarioId}/{skillId}")
	@Operation(summary = "Excluir a skill do usuario pelo ID", description = "Excluir skill")
    public void excluirSkillUsuario(@PathVariable Long usuarioId, @PathVariable Long skillId) {
        skillService.excluirSkillUsuario(skillId, usuarioId);
    }
	
}
