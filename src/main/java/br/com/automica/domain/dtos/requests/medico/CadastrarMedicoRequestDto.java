package br.com.automica.domain.dtos.requests.medico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CadastrarMedicoRequestDto {

	@NotBlank
	@Size(min = 1, max = 100, message = "Mín = 1, máx = 100 caracteres.")
	private String nomeMedico;
	
	@NotBlank
	@Pattern(regexp = "^[0-9]{11}$", message = "Obrigatório 11 caracteres somente números.")
	private String cpfMedico;
	
	@NotBlank
	@Pattern(regexp = "^[0-9A-Za-z]{4,20}$", message = "Mín. 4 - Máx. 20 caracteres, somente letras e números.")
	private String crmMedico;
	
	@NotNull(message = "Id da clínica obrigatório.")
	private Long idClinica;
}
