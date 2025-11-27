package br.com.automica.applications.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.automica.domain.dtos.requests.consulta.CadastrarConsultaRequestDto;
import br.com.automica.domain.dtos.responses.consulta.CadastrarConsultaResponseDto;
import br.com.automica.domain.entities.Consulta;
import br.com.automica.domain.enums.StatusConsulta;
import br.com.automica.domain.exceptions.NaoEncontradoException;
import br.com.automica.infrastructure.repositories.ConsultaRepository;
import br.com.automica.infrastructure.repositories.MedicoRepository;
import br.com.automica.infrastructure.repositories.PacienteRepository;
import jakarta.transaction.Transactional;

@Service
public class ConsultaService {
	
	@Autowired
	private MedicoRepository medicoRepository;
	
	@Autowired
	private PacienteRepository pacienteRepository;

	@Autowired
	private ConsultaRepository consultaRepository;

	@Transactional
	public CadastrarConsultaResponseDto cadastrarConsulta(CadastrarConsultaRequestDto request) {

		var medicoFound = medicoRepository.findById(request.getIdMedico())
				.orElseThrow(()-> new NaoEncontradoException("Médico não encontrado."));
		
		var pacienteFound = pacienteRepository.findById(request.getIdPaciente())
				.orElseThrow(()-> new NaoEncontradoException("Paciente não encontrado."));
		
		var novaConsulta = new Consulta();
		novaConsulta.setDataConsulta(request.getDataConsulta());
		novaConsulta.setHoraConsulta(request.getHoraConsulta());
		novaConsulta.setStatus(StatusConsulta.AGENDADA);
		novaConsulta.setMedico(medicoFound);
		novaConsulta.setPaciente(pacienteFound);
		medicoFound.getConsultas().add(novaConsulta);
		pacienteFound.getConsultas().add(novaConsulta);
		consultaRepository.save(novaConsulta);

		var response = new CadastrarConsultaResponseDto();
		response.setIdConsulta(novaConsulta.getIdConsulta());
		response.setDataConsulta(novaConsulta.getDataConsulta());
		response.setHoraConsulta(novaConsulta.getHoraConsulta());
		response.setNomeMedico(novaConsulta.getMedico().getNomeMedico());
		response.setNomePaciente(novaConsulta.getPaciente().getNomePaciente());
		response.setNomeClinica(novaConsulta.getMedico().getClinica().getNomeClinica());
		response.setResposta("A consulta foi agendada.");
		return response;

	}

}

