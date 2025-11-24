package br.com.automica.domain.exceptions;

public class NaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	@Override
	public String getMessage() {
		return "Não encontrado.";
	}

}
