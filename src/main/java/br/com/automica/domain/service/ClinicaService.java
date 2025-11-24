package br.com.automica.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.automica.domain.dtos.requests.clinica.CadastrarClinicaRequestDto;
import br.com.automica.domain.dtos.responses.clinica.CadastrarClinicaResponseDto;
import br.com.automica.domain.entities.Clinica;
import br.com.automica.domain.exceptions.JaCadastradoException;
import br.com.automica.infrastructure.repositories.ClinicaRepository;
import jakarta.transaction.Transactional;

@Service
public class ClinicaService {

	@Autowired
	private ClinicaRepository clinicaRepository;

	@Transactional
	public CadastrarClinicaResponseDto cadastrarClinica(CadastrarClinicaRequestDto request) {

		var clinicaFound = clinicaRepository.findByCnpjClinica(request.getCnpjClinica());

		if (clinicaFound.isPresent())
			throw new JaCadastradoException();

		var clinicaNova = new Clinica();
		clinicaNova.setNomeClinica(request.getNomeClinica());
		clinicaNova.setCnpjClinica(request.getCnpjClinica());
		clinicaRepository.save(clinicaNova);

		var response = new CadastrarClinicaResponseDto();
		response.setIdClinica(clinicaNova.getIdClinica());
		response.setNomeClinica(clinicaNova.getNomeClinica());
		response.setCnpjClinica(request.getCnpjClinica());
		response.setResposta("Clínica foi cadastrada no sistema.");
		return response;
	}

}
