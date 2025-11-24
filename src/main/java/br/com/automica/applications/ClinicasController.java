package br.com.automica.applications;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.automica.domain.dtos.requests.clinica.CadastrarClinicaRequestDto;
import br.com.automica.domain.dtos.responses.clinica.CadastrarClinicaResponseDto;
import br.com.automica.domain.service.ClinicaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/clinicas")
public class ClinicasController {
	
	@Autowired ClinicaService clinicaService;
	
	@PostMapping("cadastrar")
	public ResponseEntity<CadastrarClinicaResponseDto> post(@Valid @RequestBody CadastrarClinicaRequestDto request){
		var response = clinicaService.cadastrarClinica(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

}
