package br.com.automica.domain.dtos.responses.paciente;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CadastrarPacienteResponseDto {
	
	private Long idPaciente;
	private String nomePaciente;
	private String cpfPaciente;
	private String whatsAppPaciente;
	private String resposta;

}
