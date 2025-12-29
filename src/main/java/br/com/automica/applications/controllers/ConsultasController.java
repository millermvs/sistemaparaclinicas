package br.com.automica.applications.controllers;

import java.time.LocalDate;
import java.util.List;

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

import br.com.automica.domain.dtos.requests.consulta.CadastrarConsultaRequestDto;
import br.com.automica.domain.dtos.requests.consulta.RemarcarConsultaRequestDto;
import br.com.automica.domain.dtos.responses.consulta.ConsultaResponseDto;
import br.com.automica.domain.dtos.responses.consulta.QuantidadeConsultasPorMedicoResponseDto;
import br.com.automica.domain.service.ConsultaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/consultas")
public class ConsultasController {

	@Autowired
	private ConsultaService consultaService;

	@GetMapping("medicos/resumo/{dataInicial}/{dataFinal}")
	public ResponseEntity<List<QuantidadeConsultasPorMedicoResponseDto>> getQuantidadeConsultasPorMedico(
			@PathVariable LocalDate dataInicial, @PathVariable LocalDate dataFinal) {
		var response = consultaService.buscarQuantidadePorMedico(dataInicial, dataFinal);
		return ResponseEntity.ok(response);
	}

	@PostMapping("agendar")
	public ResponseEntity<ConsultaResponseDto> post(@Valid @RequestBody CadastrarConsultaRequestDto request) {
		var response = consultaService.cadastrarConsulta(request);
		return ResponseEntity.status(HttpStatus.CREATED.value()).body(response);
	}

	@PutMapping("{idConsulta}/finalizar")
	public ResponseEntity<ConsultaResponseDto> putFinalizar(@PathVariable Long idConsulta) {
		var response = consultaService.finalizarConsulta(idConsulta);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PutMapping("remarcar/{idConsulta}")
	public ResponseEntity<ConsultaResponseDto> put(@PathVariable Long idConsulta,
			@Valid @RequestBody RemarcarConsultaRequestDto request) {
		var response = consultaService.remarcarConsulta(idConsulta, request);
		return ResponseEntity.ok(response);
	}

	@GetMapping("datas/{dataInicio}/{dataFim}")
	public ResponseEntity<Page<ConsultaResponseDto>> getByDate(@PathVariable LocalDate dataInicio,
			@PathVariable LocalDate dataFim, @RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "10") Integer size) {
		var response = consultaService.consultarPorData(dataInicio, dataFim, page, size);
		return ResponseEntity.ok(response);
	}

	@GetMapping("medico")
	public ResponseEntity<Page<ConsultaResponseDto>> getByDateAndMedico(@RequestParam Long idMedico,
			@RequestParam LocalDate dataInicio, @RequestParam LocalDate dataFim,
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
		var response = consultaService.consultarPorDataMedico(idMedico, dataInicio, dataFim, page, size);
		return ResponseEntity.ok(response);
	}

	@PutMapping("{idConsulta}/cancelar")
	public ResponseEntity<ConsultaResponseDto> putDesmarcar(@PathVariable Long idConsulta) {
		var response = consultaService.desmarcarConsulta(idConsulta);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

}
