package br.com.automica.domain.dtos.requests.clinica;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RemarcarConsultaRequestDto {
	
	@NotNull
	private Long idConsuta;
	
	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Sao_Paulo")
	private LocalDate dataConsulta;

	@NotNull
	@JsonFormat(pattern = "HH:mm", timezone = "America/Sao_Paulo")
	private LocalTime horaConsulta;

	@NotNull
	private Long idMedico;

	@NotNull
	private Long idPaciente;

}
