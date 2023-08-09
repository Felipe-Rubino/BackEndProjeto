package br.com.neki.projeto.domain;

import java.sql.Types;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name= "imagem")
public class Imagem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "img_cd_id")
	private UUID imagemId;
	
	@Lob
	@JdbcTypeCode(Types.BINARY)
	@Column(name = "img_tx_dados")
	private byte[] dados;
	
	@JsonIgnore
	@Column(name = "img_tx_nome")
	private String nome;
	
	@JsonIgnore
	@Column(name = "img_tx_tipo")
	private String tipo;
	
	@OneToOne(mappedBy="imagem", cascade=CascadeType.REMOVE)
	private Skill skill;
	
	public Imagem() {
		
	}

	public Imagem(byte[] dados, String nome, String tipo) {
		this.dados = dados;
		this.nome = nome;
		this.tipo = tipo;
	}

	public UUID getImagemId() {
		return imagemId;
	}

	public void setImagemId(UUID imagemId) {
		this.imagemId = imagemId;
	}

	public byte[] getDados() {
		return dados;
	}

	public void setDados(byte[] dados) {
		this.dados = dados;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	
}
