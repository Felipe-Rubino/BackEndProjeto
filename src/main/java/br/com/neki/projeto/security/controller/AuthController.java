package br.com.neki.projeto.security.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.neki.projeto.domain.Usuario;
import br.com.neki.projeto.repository.UsuarioRepository;
import br.com.neki.projeto.security.domain.Role;
import br.com.neki.projeto.security.domain.User;
import br.com.neki.projeto.security.dto.JwtResponseDTO;
import br.com.neki.projeto.security.dto.LoginRequestDTO;
import br.com.neki.projeto.security.dto.MessageResponseDTO;
import br.com.neki.projeto.security.dto.SignupRequestDTO;
import br.com.neki.projeto.security.enums.RoleEnum;
import br.com.neki.projeto.security.jwt.JwtUtils;
import br.com.neki.projeto.security.repository.RoleRepository;
import br.com.neki.projeto.security.repository.UserRepository;
import br.com.neki.projeto.security.service.UserDetailsImpl;
import jakarta.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	UsuarioRepository usuarioRepository;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequestDTO loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.toList();

		return ResponseEntity.ok(new JwtResponseDTO(jwt, userDetails.getId(), userDetails.getEmail(), roles, userDetails.getLoggedUserId()));

	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequestDTO signUpRequest) {
	    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
	        return ResponseEntity.badRequest().body(new MessageResponseDTO("Erro: Email já utilizado!"));
	    }
	    Usuario usuario = new Usuario();
	    usuario.setNome(signUpRequest.getUsername());
	    usuario = usuarioRepository.save(usuario);

	
	    User user = new User(signUpRequest.getEmail(),
	            encoder.encode(signUpRequest.getPassword()),
	            usuario
	            );

	    Set<String> strRoles = signUpRequest.getRole();
	    Set<Role> roles = new HashSet<>();

	    if (strRoles == null) {
	        Role userRole = roleRepository.findByName(RoleEnum.ROLE_USER)
	                .orElseThrow(() -> new RuntimeException("Erro: Role não encontrada."));
	        roles.add(userRole);
	    } else {
	        Role userRole = roleRepository.findByName(RoleEnum.ROLE_USER)
	                .orElseThrow(() -> new RuntimeException("Erro: Role não encontrada."));
	        roles.add(userRole);
	    }

	    user.setRoles(roles);
	    userRepository.save(user);

	    return ResponseEntity.ok(new MessageResponseDTO("Usuário registrado com sucesso!"));
	}
}
