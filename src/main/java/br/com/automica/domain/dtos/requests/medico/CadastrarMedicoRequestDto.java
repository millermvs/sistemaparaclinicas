package br.com.automica.domain.dtos.requests.medico;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CadastrarMedicoRequestDto {

	private String nomeMedico;
	private String cpfMedico;
	private Long idClinica;
}
