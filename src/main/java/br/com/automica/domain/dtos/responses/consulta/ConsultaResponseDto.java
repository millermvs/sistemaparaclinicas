package br.com.automica.domain.dtos.responses.consulta;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConsultaResponseDto {
	
	private Long idConsulta;
	private Long idMedico;
	private Long idPaciente;
	private LocalDate dataConsulta;	
	@JsonFormat(pattern = "HH:mm", timezone = "America/Sao_Paulo")
	private LocalTime horaConsulta;
	private String nomeMedico;
	private String nomePaciente;
	private String status;
	private String nomeClinica;
}
