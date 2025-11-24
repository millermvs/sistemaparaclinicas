package br.com.automica.domain.dtos.requests.paciente;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CadastrarPacienteRequestDto {

	@NotBlank
	@Pattern(regexp = "^[A-Za-zÀ-Üà-ü\\s]{1,100}$", message = "Somente letras e espaços. Min. 1 e Máx. 100 caracteres.")
	private String nomePaciente;
	
	@NotBlank
	@Pattern(regexp = "^[0-9]{11}$", message = "Obrigatório 11 números.")
	private String cpfPaciente;
	
	@NotBlank
	@Pattern(regexp = "^[0-9]{12,13}$", message = "Somente números. Min. 12 e Máx. 13 caracteres.")
	private String whatsAppPaciente;
}
