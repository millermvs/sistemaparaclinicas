package br.com.automica.domain.dtos.responses.medico;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConsultarMedicoResponseDto {
	private Long idMedico;
	private String nomeMedico;
	private String cpfMedico;
	private String crmMedico;
	private String whatsAppMedico;
}
