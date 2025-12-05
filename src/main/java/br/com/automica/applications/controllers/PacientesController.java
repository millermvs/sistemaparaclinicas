package br.com.automica.applications.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.automica.domain.dtos.requests.paciente.CadastrarPacienteRequestDto;
import br.com.automica.domain.dtos.responses.paciente.CadastrarPacienteResponseDto;
import br.com.automica.domain.service.PacienteService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/pacientes")
public class PacientesController {

	@Autowired
	private PacienteService pacienteService;

	@PostMapping("cadastrar")
	public ResponseEntity<CadastrarPacienteResponseDto> post(@Valid @RequestBody CadastrarPacienteRequestDto request) {
		var response = pacienteService.cadastrarPaciente(request);
		return ResponseEntity.status(HttpStatus.CREATED.value()).body(response);
	}
}
