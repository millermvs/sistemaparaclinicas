package br.com.automica.domain.dtos.responses.consulta;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConsultaResponseDto {
	private Long idConsulta;	
	private LocalDate dataConsulta;
	private LocalTime horaConsulta;
	private String nomeMedico;
	private String nomePaciente;
	private String status;
	private String nomeClinica;
}
