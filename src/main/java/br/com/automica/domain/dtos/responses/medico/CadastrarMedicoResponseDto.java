package br.com.automica.domain.dtos.responses.medico;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CadastrarMedicoResponseDto {

	private Long idMedico;
	private String nomeMedico;
	private String nomeClinica;
	private String resposta;
}
