package br.com.automica.applications.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.automica.domain.dtos.requests.consulta.CadastrarConsultaRequestDto;
import br.com.automica.domain.dtos.responses.consulta.CadastrarConsultaResponseDto;
import br.com.automica.domain.service.ConsultaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/consultas")
public class ConsultasController {
	
	@Autowired
	private ConsultaService consultaService;

	@PostMapping("agendar")
	public ResponseEntity<CadastrarConsultaResponseDto> post(@Valid @RequestBody CadastrarConsultaRequestDto request) {
		var response = consultaService.cadastrarConsulta(request);
		return ResponseEntity.status(HttpStatus.CREATED.value()).body(response);
	}

}
