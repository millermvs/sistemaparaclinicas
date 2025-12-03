package br.com.automica.domain.dtos.responses.medico;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeletarMedicoResponseDto {
	private Long idMedico;
	private String nomeMedico;
	private String cpfMedico;
	private String crmMedico;
	private String whatsAppMedico;
	private String resposta;
}
