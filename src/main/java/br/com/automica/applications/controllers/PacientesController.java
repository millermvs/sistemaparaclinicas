package br.com.automica.applications.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.automica.domain.dtos.requests.paciente.CadastrarPacienteRequestDto;
import br.com.automica.domain.dtos.requests.paciente.EditarPacienteRequestDto;
import br.com.automica.domain.dtos.responses.paciente.CadastrarPacienteResponseDto;
import br.com.automica.domain.dtos.responses.paciente.ConsultarPacienteResponseDto;
import br.com.automica.domain.dtos.responses.paciente.EditarPacienteResponseDto;
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

	@GetMapping
	public ResponseEntity<Page<ConsultarPacienteResponseDto>> get(@RequestParam String nome,
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "5") Integer size) {
		var response = pacienteService.consultarPaciente(nome, page, size);
		return ResponseEntity.ok(response);
	}

	@PutMapping("editar/{id}")
	public ResponseEntity<EditarPacienteResponseDto> put(@PathVariable Long id,
			@Valid @RequestBody EditarPacienteRequestDto request) {
		var response = pacienteService.editarPaciente(id, request);
		return ResponseEntity.ok(response);
	}
}
