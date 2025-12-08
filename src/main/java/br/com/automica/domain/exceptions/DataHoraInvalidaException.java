package br.com.automica.domain.exceptions;

public class DataHoraInvalidaException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DataHoraInvalidaException() {
		super("Data inválida");
	}
	
	public DataHoraInvalidaException(String mensagem) {
		super(mensagem);
	}

}
