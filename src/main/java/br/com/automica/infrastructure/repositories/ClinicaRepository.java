package br.com.automica.infrastructure.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.automica.domain.entities.Clinica;

@Repository
public interface ClinicaRepository extends JpaRepository<Clinica, Long> {

	@EntityGraph(attributePaths = "medicos")
	Optional<Clinica> findByIdClinica(Long idClinica);
	
	@EntityGraph(attributePaths = "medicos")
	Optional<Clinica> findByCnpjClinica(String cnpjClinica);
}
