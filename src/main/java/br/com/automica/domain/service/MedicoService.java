package br.com.automica.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.automica.domain.dtos.requests.medico.CadastrarMedicoRequestDto;
import br.com.automica.domain.dtos.responses.clinica.ListarMedicosResponseDto;
import br.com.automica.domain.dtos.responses.medico.CadastrarMedicoResponseDto;
import br.com.automica.domain.dtos.responses.medico.ConsultarMedicoResponseDto;
import br.com.automica.domain.entities.Medico;
import br.com.automica.domain.exceptions.JaCadastradoException;
import br.com.automica.domain.exceptions.NaoEncontradoException;
import br.com.automica.infrastructure.repositories.ClinicaRepository;
import br.com.automica.infrastructure.repositories.MedicoRepository;
import jakarta.transaction.Transactional;

@Service
public class MedicoService {

	@Autowired
	private MedicoRepository medicoRepository;

	@Autowired
	private ClinicaRepository clinicaRepository;

	public List<ConsultarMedicoResponseDto> consultarMedico(String nome) {

		var listMedicosFound = medicoRepository.findByNomeMedicoContainingIgnoreCaseOrderByNomeMedicoAsc(nome);

		if (listMedicosFound.isEmpty())
			throw new NaoEncontradoException();

		List<ConsultarMedicoResponseDto> listMedicos = new ArrayList<ConsultarMedicoResponseDto>();

		for (var medico : listMedicosFound) {
			var dtoItem = new ConsultarMedicoResponseDto();
			dtoItem.setIdMedico(medico.getIdMedico());
			dtoItem.setNomeMedico(medico.getNomeMedico());
			dtoItem.setCpfMedico(medico.getCpfMedico());
			dtoItem.setCrmMedico(medico.getCrmMedico());
			dtoItem.setWhatsAppMedico(medico.getWhatsAppMedico());
			listMedicos.add(dtoItem);
		}
		return listMedicos;
	}

	public Page<ListarMedicosResponseDto> listarMedicos(Long idClinica, Integer page, Integer size) {

		clinicaRepository.findById(idClinica).orElseThrow(() -> new NaoEncontradoException("Clínica não encontrada."));

		var pageable = PageRequest.of(page, size, Sort.by("nomeMedico").ascending());

		var paginaMedicos = medicoRepository.findByClinicaIdClinica(idClinica, pageable);

		return paginaMedicos.map(medico -> {
			var dtoItem = new ListarMedicosResponseDto();
			dtoItem.setIdMedico(medico.getIdMedico());
			dtoItem.setNomeMedico(medico.getNomeMedico());
			dtoItem.setCpfMedico(medico.getCpfMedico());
			dtoItem.setCrmMedico(medico.getCrmMedico());
			dtoItem.setWhatsAppMedico(medico.getWhatsAppMedico());
			return dtoItem;
		});
	}

	@Transactional
	public CadastrarMedicoResponseDto cadastrarMedico(CadastrarMedicoRequestDto request) {

		var clinicaFound = clinicaRepository.findById(request.getIdClinica())
				.orElseThrow(() -> new NaoEncontradoException("Clínica não encontrada."));

		var cpfMedicoFound = medicoRepository.findByCpfMedico(request.getCpfMedico());
		if (cpfMedicoFound.isPresent())
			throw new JaCadastradoException("CPF já cadastrado no sitema.");

		var crmMedicoFound = medicoRepository.findByCrmMedico(request.getCrmMedico());
		if (crmMedicoFound.isPresent())
			throw new JaCadastradoException("CRM já cadastrado no sitema.");

		var novoMedico = new Medico();
		novoMedico.setNomeMedico(request.getNomeMedico());
		novoMedico.setCpfMedico(request.getCpfMedico());
		novoMedico.setCrmMedico(request.getCrmMedico());
		novoMedico.setWhatsAppMedico(request.getWhatsAppMedico());
		clinicaFound.getMedicos().add(novoMedico);
		novoMedico.setClinica(clinicaFound);
		medicoRepository.save(novoMedico);

		var response = new CadastrarMedicoResponseDto();
		response.setIdMedico(novoMedico.getIdMedico());
		response.setNomeMedico(novoMedico.getNomeMedico());
		response.setNomeClinica(novoMedico.getClinica().getNomeClinica());
		response.setResposta("Médico foi cadastrado no sistema.");

		return response;
	}
}
