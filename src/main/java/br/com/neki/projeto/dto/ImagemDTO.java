package br.com.neki.projeto.dto;

import java.util.UUID;

public class ImagemDTO {
	
	private UUID imagemId;
	private byte[] dados;
	private String nome;
	private String tipo;
	
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
