package br.com.automica.domain.dtos.responses.clinica;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CadastrarClinicaResponseDto {

	private Long idClinica;
	private String nomeClinica;
	private String cnpjClinica;
	private String resposta;
}
