package br.com.neki.projeto.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.neki.projeto.domain.Imagem;

public interface ImagemRepository extends JpaRepository<Imagem, UUID> {

}
