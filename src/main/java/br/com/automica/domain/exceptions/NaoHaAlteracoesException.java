package br.com.automica.domain.exceptions;

public class NaoHaAlteracoesException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NaoHaAlteracoesException() {
		super("Não há alterações");
	}
	
	public NaoHaAlteracoesException(String menssagem) {
		super(menssagem);
	}
}
