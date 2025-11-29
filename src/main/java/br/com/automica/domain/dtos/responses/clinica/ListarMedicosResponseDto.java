package br.com.automica.domain.dtos.responses.clinica;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListarMedicosResponseDto {

	private String nomeMedico;
	private String cpfMedico;
	private String crmMedico;
	private String whatsAppMedico;
	
}
