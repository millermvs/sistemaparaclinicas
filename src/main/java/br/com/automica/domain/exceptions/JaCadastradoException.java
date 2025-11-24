package br.com.automica.domain.exceptions;

public class JaCadastradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "Já cadastrado no sistema.";
	}

}
