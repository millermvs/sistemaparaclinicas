package br.com.automica.domain.dtos.responses.clinica;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListarPacientesResponseDto {
	private Long idPaciente;
	private String nomePaciente;
	private String cpfPaciente;
	private String whatsAppPaciente;
}
