package br.com.automica.domain.dtos.responses.consulta;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuantidadeConsultasPorMedicoResponseDto {
	
	private String nomeMedico;
	private Long quantidadeConsultas;

}
