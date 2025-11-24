package br.com.automica.domain.dtos.requests.clinica;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
	@Pattern(regexp = "^[0-9A-Z]{14}$", message = "Obrigatório 14 caracteres. Números ou letras maiúsculas")
	private String cnpjClinica;
}
