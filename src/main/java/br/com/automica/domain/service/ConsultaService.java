package br.com.automica.domain.service;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.automica.domain.dtos.requests.consulta.CadastrarConsultaRequestDto;
import br.com.automica.domain.dtos.responses.consulta.ConsultaResponseDto;
import br.com.automica.domain.entities.Consulta;
import br.com.automica.domain.enums.StatusConsulta;
import br.com.automica.domain.exceptions.DataHoraInvalidaException;
import br.com.automica.domain.exceptions.NaoEncontradoException;
import br.com.automica.infrastructure.repositories.ConsultaRepository;
import br.com.automica.infrastructure.repositories.MedicoRepository;
import br.com.automica.infrastructure.repositories.PacienteRepository;

@Service
public class ConsultaService {

	@Autowired
	private MedicoRepository medicoRepository;

	@Autowired
	private PacienteRepository pacienteRepository;

	@Autowired
	private ConsultaRepository consultaRepository;

	private ConsultaResponseDto createResponse(Consulta consulta) {
		var response = new ConsultaResponseDto();
		response.setIdConsulta(consulta.getIdConsulta());
		response.setDataConsulta(consulta.getDataConsulta());
		response.setHoraConsulta(consulta.getHoraConsulta());
		response.setNomeMedico(consulta.getMedico().getNomeMedico());
		response.setNomePaciente(consulta.getPaciente().getNomePaciente());
		response.setNomeClinica(consulta.getMedico().getClinica().getNomeClinica());
		response.setStatus(consulta.getStatus().toString());
		return response;
	}

	@Transactional(readOnly = true)
	public Page<ConsultaResponseDto> consultarPorDataMedico(Long idMedico, LocalDate dataInicio, LocalDate dataFim,
			Integer page, Integer size) {

		if (dataInicio.isAfter(dataFim))
			throw new DataHoraInvalidaException("Intervalo de datas inválido: data inicial posterior à data final.");

		medicoRepository.findById(idMedico).orElseThrow(() -> new NaoEncontradoException("Médico não encontrado."));

		var pageable = PageRequest.of(page, size, Sort.by("dataConsulta"));

		var paginaConsultas = consultaRepository.findByDataConsultaBetweenAndMedicoIdMedico(dataInicio, dataFim, idMedico,
				pageable);

		return paginaConsultas.map(consulta -> {
			var response = createResponse(consulta);
			return response;
		});
	}

	@Transactional(readOnly = true)
	public Page<ConsultaResponseDto> consultarPorData(LocalDate dataInicio, LocalDate dataFim, Integer page,
			Integer size) {

		if (dataInicio.isAfter(dataFim))
			throw new DataHoraInvalidaException("Intervalo de datas inválido: data inicial posterior à data final.");

		var pageable = PageRequest.of(page, size, Sort.by("dataConsulta"));

		var paginaConsultas = consultaRepository.findByDataConsultaBetween(dataInicio, dataFim, pageable);

		if (paginaConsultas.isEmpty())
			throw new NaoEncontradoException("Nenhuma consulta encontrada");

		return paginaConsultas.map(consulta -> {
			var response = createResponse(consulta);
			return response;
		});
	}

	@Transactional
	public ConsultaResponseDto cadastrarConsulta(CadastrarConsultaRequestDto request) {

		var dataAtual = LocalDate.now();
		var horaAtual = LocalTime.now().withNano(0);

		if (dataAtual.isAfter(request.getDataConsulta()))
			throw new DataHoraInvalidaException("Data final inválida, selecione uma data futura.");

		if (dataAtual.isEqual(request.getDataConsulta()) && horaAtual.isAfter(request.getHoraConsulta()))
			throw new DataHoraInvalidaException("Hora inválida, selecione uma hora futura.");

		var medicoFound = medicoRepository.findById(request.getIdMedico())
				.orElseThrow(() -> new NaoEncontradoException("Médico não encontrado."));

		var pacienteFound = pacienteRepository.findById(request.getIdPaciente())
				.orElseThrow(() -> new NaoEncontradoException("Paciente não encontrado."));

		var consultaFoundMedico = consultaRepository.findByDataConsultaHoraConsultaMedicoIdMedico(
				request.getDataConsulta(), request.getHoraConsulta(), request.getIdMedico());

		if (consultaFoundMedico.isPresent())
			throw new DataHoraInvalidaException("Horário indisponível para o médico informado.");

		var consultaFoundPaciente = consultaRepository.findByDataConsultaHoraConsultaPacienteIdPaciente(
				request.getDataConsulta(), request.getHoraConsulta(), request.getIdPaciente());

		if (consultaFoundPaciente.isPresent())
			throw new DataHoraInvalidaException("Paciente com agendamento neste horário.");

		var novaConsulta = new Consulta();
		novaConsulta.setDataConsulta(request.getDataConsulta());
		novaConsulta.setHoraConsulta(request.getHoraConsulta());
		novaConsulta.setStatus(StatusConsulta.AGENDADA);
		novaConsulta.setMedico(medicoFound);
		novaConsulta.setPaciente(pacienteFound);
		medicoFound.getConsultas().add(novaConsulta);
		pacienteFound.getConsultas().add(novaConsulta);
		consultaRepository.save(novaConsulta);

		return createResponse(novaConsulta);
	}
}
