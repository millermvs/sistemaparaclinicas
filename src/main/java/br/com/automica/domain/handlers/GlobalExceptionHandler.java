package br.com.automica.domain.handlers;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.automica.domain.exceptions.JaCadastradoException;
import br.com.automica.domain.exceptions.NaoEncontradoException;
import br.com.automica.domain.exceptions.NaoHaAlteracoesException;
import br.com.automica.domain.exceptions.RegraNegocioException;

@ControllerAdvice
public class GlobalExceptionHandler {

	public ResponseEntity<Object> createResponse(HttpStatus status, Exception ex) {
		Map<String, Object> body = new HashMap<String, Object>();
		body.put("datetime", LocalDateTime.now());
		body.put("status", status.value());
		body.put("message", ex.getMessage());

		return ResponseEntity.status(status.value()).body(body);
	}

	@ExceptionHandler(JaCadastradoException.class)
	public ResponseEntity<Object> handlerJaCadastradoException(JaCadastradoException ex) {
		return createResponse(HttpStatus.CONFLICT, ex);
	}

	@ExceptionHandler(NaoEncontradoException.class)
	public ResponseEntity<Object> handlerNaoEncontrado(NaoEncontradoException ex) {
		return createResponse(HttpStatus.NOT_FOUND, ex);
	}

	@ExceptionHandler(NaoHaAlteracoesException.class)
	public ResponseEntity<Object> handlerNaoHaAlteracoes(NaoHaAlteracoesException ex) {
		return createResponse(HttpStatus.BAD_REQUEST, ex);
	}

	@ExceptionHandler(RegraNegocioException.class)
	public ResponseEntity<Object> handlerRegraNegocio(RegraNegocioException ex) {
		return createResponse(HttpStatus.UNPROCESSABLE_CONTENT, ex);
	}
}
