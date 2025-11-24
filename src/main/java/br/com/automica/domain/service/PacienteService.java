package br.com.automica.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.automica.domain.dtos.requests.paciente.CadastrarPacienteRequestDto;
import br.com.automica.domain.dtos.responses.paciente.CadastrarPacienteResponseDto;
import br.com.automica.domain.entities.Paciente;
import br.com.automica.domain.exceptions.JaCadastradoException;
import br.com.automica.infrastructure.repositories.PacienteRepository;

@Service
public class PacienteService {
	
	@Autowired
	private PacienteRepository pacienteRepository;

	public CadastrarPacienteResponseDto cadastrarPaciente(CadastrarPacienteRequestDto request) {

		var pacienteFound = pacienteRepository.findByCpfPaciente(request.getCpfPaciente());
		
		if (pacienteFound.isPresent())
			throw new JaCadastradoException();
		
		var novoPaciente = new Paciente();
		novoPaciente.setNomePaciente(request.getNomePaciente());
		novoPaciente.setCpfPaciente(request.getCpfPaciente());
		novoPaciente.setWhatsAppPaciente(request.getWhatsAppPaciente());
		pacienteRepository.save(novoPaciente);
		
		var response = new CadastrarPacienteResponseDto();
		response.setIdPaciente(novoPaciente.getIdPaciente());
		response.setNomePaciente(novoPaciente.getNomePaciente());
		response.setCpfPaciente(novoPaciente.getCpfPaciente());
		response.setWhatsAppPaciente(novoPaciente.getWhatsAppPaciente());
		response.setResposta("Novo paciente foi cadastrado no sistema.");
		return response;
	}
}
