package br.com.automica.applications.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.automica.domain.dtos.requests.medico.CadastrarMedicoRequestDto;
import br.com.automica.domain.dtos.responses.medico.CadastrarMedicoResponseDto;
import br.com.automica.domain.dtos.responses.medico.ConsultarMedicoResponseDto;
import br.com.automica.domain.service.MedicoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/medicos")
public class MedicosController {

	@Autowired
	private MedicoService medicoService;

	@PostMapping("cadastrar")
	public ResponseEntity<CadastrarMedicoResponseDto> post(@Valid @RequestBody CadastrarMedicoRequestDto request) {
		var response = medicoService.cadastrarMedico(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@GetMapping("consultar/{nome}")
	public ResponseEntity<List<ConsultarMedicoResponseDto>> get(@PathVariable String nome){
		var response = medicoService.consultarMedico(nome);
		return ResponseEntity.ok(response);
	}

}
