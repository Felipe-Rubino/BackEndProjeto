package br.com.neki.projeto.exceptions;

public class NotFoundExeception extends RuntimeException {
	
	private static final long serialVersionUID = 3454640414540638688L;

	public NotFoundExeception(String message) {
		super(message);
	}

}
