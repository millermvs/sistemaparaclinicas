package br.com.automica.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.automica.domain.dtos.requests.medico.CadastrarMedicoRequestDto;
import br.com.automica.domain.dtos.responses.medico.CadastrarMedicoResponseDto;
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

	@Transactional
	public CadastrarMedicoResponseDto cadastrarMedico(CadastrarMedicoRequestDto request) {

		var clinicaFound = clinicaRepository.findByIdClinica(request.getIdClinica())
				.orElseThrow(NaoEncontradoException::new);

		var medicoFound = medicoRepository.findByCpfMedico(request.getCpfMedico());
			if (medicoFound.isPresent())
				throw new JaCadastradoException();

		var novoMedico = new Medico();
		novoMedico.setNomeMedico(request.getNomeMedico());
		novoMedico.setCpfMedico(request.getCpfMedico());
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
