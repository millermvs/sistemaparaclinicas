package br.com.automica.applications.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.automica.domain.dtos.requests.clinica.CadastrarClinicaRequestDto;
import br.com.automica.domain.dtos.responses.clinica.CadastrarClinicaResponseDto;
import br.com.automica.domain.dtos.responses.clinica.ListarMedicosResponseDto;
import br.com.automica.domain.service.ClinicaService;
import br.com.automica.domain.service.MedicoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/clinicas")
public class ClinicasController {

	@Autowired
	private ClinicaService clinicaService;

	@Autowired
	private MedicoService medicoService;

	@PostMapping("cadastrar")
	public ResponseEntity<CadastrarClinicaResponseDto> post(@Valid @RequestBody CadastrarClinicaRequestDto request) {
		var response = clinicaService.cadastrarClinica(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@GetMapping("/{idClinica}/medicos")
	public ResponseEntity<Page<ListarMedicosResponseDto>> getAllMedicos(@PathVariable Long idClinica,
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "5") Integer size) {
		var response = medicoService.listarMedicos(idClinica, page, size);
		return ResponseEntity.ok(response);
	}

}
