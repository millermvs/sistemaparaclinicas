package br.com.automica.infrastructure.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.automica.domain.entities.Medico;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long>{
	
	@EntityGraph(attributePaths = "clinica")
	Optional<Medico> findByCpfMedico(String cpfmedico);

}
