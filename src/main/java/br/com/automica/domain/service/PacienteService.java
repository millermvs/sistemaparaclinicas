package br.com.automica.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.automica.domain.dtos.requests.paciente.CadastrarPacienteRequestDto;
import br.com.automica.domain.dtos.requests.paciente.EditarPacienteRequestDto;
import br.com.automica.domain.dtos.responses.clinica.ListarPacientesResponseDto;
import br.com.automica.domain.dtos.responses.paciente.CadastrarPacienteResponseDto;
import br.com.automica.domain.dtos.responses.paciente.ConsultarPacienteResponseDto;
import br.com.automica.domain.dtos.responses.paciente.EditarPacienteResponseDto;
import br.com.automica.domain.entities.Paciente;
import br.com.automica.domain.exceptions.JaCadastradoException;
import br.com.automica.domain.exceptions.NaoEncontradoException;
import br.com.automica.domain.exceptions.NaoHaAlteracoesException;
import br.com.automica.infrastructure.repositories.ClinicaRepository;
import br.com.automica.infrastructure.repositories.PacienteRepository;

@Service
public class PacienteService {

	@Autowired
	private PacienteRepository pacienteRepository;

	@Autowired
	private ClinicaRepository clinicaRepository;

	@Transactional
	public EditarPacienteResponseDto editarPaciente(Long idPaciente, EditarPacienteRequestDto request) {
		var pacienteFound = pacienteRepository.findById(idPaciente)
				.orElseThrow(() -> new NaoEncontradoException("Paciente não encontrado."));

		if (pacienteFound.getNomePaciente().equals(request.getNomePaciente())
				&& pacienteFound.getCpfPaciente().equals(request.getCpfPaciente())
				&& pacienteFound.getWhatsAppPaciente().equals(request.getWhatsAppPaciente()))
			throw new NaoHaAlteracoesException("Não foi alterado nenhum dado do paciente.");

		pacienteFound.setNomePaciente(request.getNomePaciente());
		pacienteFound.setCpfPaciente(request.getCpfPaciente());
		pacienteFound.setWhatsAppPaciente(request.getWhatsAppPaciente());

		var response = new EditarPacienteResponseDto();
		response.setIdPaciente(pacienteFound.getIdPaciente());
		response.setNomePaciente(pacienteFound.getNomePaciente());
		response.setCpfPaciente(pacienteFound.getCpfPaciente());
		response.setWhatsAppPaciente(pacienteFound.getWhatsAppPaciente());
		response.setResposta("Dados do paciente foram alterados.");

		return response;
	}

	@Transactional(readOnly = true)
	public Page<ConsultarPacienteResponseDto> consultarPaciente(String nome, Integer page, Integer size) {

		var pageable = PageRequest.of(page, size, Sort.by("nomePaciente").ascending());

		var paginaPacientes = pacienteRepository.findByNomePacienteContainingIgnoreCaseOrderByNomePaciente(nome,
				pageable);

		if (paginaPacientes.isEmpty())
			throw new NaoEncontradoException("Nenhum paciente encontrado.");

		return paginaPacientes.map(paciente -> {
			var dtoItem = new ConsultarPacienteResponseDto();
			dtoItem.setIdPaciente(paciente.getIdPaciente());
			dtoItem.setNomePaciente(paciente.getNomePaciente());
			dtoItem.setCpfPaciente(paciente.getCpfPaciente());
			dtoItem.setWhatsAppPaciente(paciente.getWhatsAppPaciente());
			return dtoItem;
		});
	}

	@Transactional(readOnly = true)
	public Page<ListarPacientesResponseDto> listarPacientes(Long idClinica, Integer page, Integer size) {
		clinicaRepository.findById(idClinica).orElseThrow(() -> new NaoEncontradoException("Clínica não encontrada."));

		var pageable = PageRequest.of(page, size, Sort.by("nomePaciente").ascending());

		var pagePaciente = pacienteRepository.findByClinicaIdClinica(idClinica, pageable);

		return pagePaciente.map(paciente -> {
			var dtoItem = new ListarPacientesResponseDto();
			dtoItem.setIdPaciente(paciente.getIdPaciente());
			dtoItem.setNomePaciente(paciente.getNomePaciente());
			dtoItem.setCpfPaciente(paciente.getCpfPaciente());
			dtoItem.setWhatsAppPaciente(paciente.getWhatsAppPaciente());
			return dtoItem;
		});
	}

	@Transactional
	public CadastrarPacienteResponseDto cadastrarPaciente(CadastrarPacienteRequestDto request) {

		var clinicaFound = clinicaRepository.findById(request.getIdClinica())
				.orElseThrow(() -> new NaoEncontradoException("Clínica não encontrada."));

		var pacienteFound = pacienteRepository.findByCpfPaciente(request.getCpfPaciente());

		if (pacienteFound.isPresent())
			throw new JaCadastradoException("CPF já cadastrado no sistema.");

		var novoPaciente = new Paciente();
		novoPaciente.setNomePaciente(request.getNomePaciente());
		novoPaciente.setCpfPaciente(request.getCpfPaciente());
		novoPaciente.setWhatsAppPaciente(request.getWhatsAppPaciente());
		novoPaciente.setClinica(clinicaFound);
		clinicaFound.getPacientes().add(novoPaciente);
		pacienteRepository.save(novoPaciente);

		var response = new CadastrarPacienteResponseDto();
		response.setIdPaciente(novoPaciente.getIdPaciente());
		response.setNomePaciente(novoPaciente.getNomePaciente());
		response.setCpfPaciente(novoPaciente.getCpfPaciente());
		response.setWhatsAppPaciente(novoPaciente.getWhatsAppPaciente());
		response.setNomeClinica(novoPaciente.getClinica().getNomeClinica());
		response.setResposta("Novo paciente foi cadastrado no sistema.");
		return response;
	}
}
