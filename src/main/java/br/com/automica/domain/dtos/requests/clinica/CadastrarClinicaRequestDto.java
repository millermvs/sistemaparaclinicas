package br.com.automica.domain.dtos.requests.clinica;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CadastrarClinicaRequestDto {

	@NotBlank
	@Size(min = 1, max = 100, message = "Mín = 1, máx = 100 caracteres.")
	private String nomeClinica;
	
	@NotBlank
	@Size(min = 1, max = 15, message = "Mín = 1, máx = 100 caracteres.")
	private String cnpjClinica;
}
