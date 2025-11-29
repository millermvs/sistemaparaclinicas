package br.com.automica.infrastructure.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.automica.domain.entities.Medico;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {

	@EntityGraph(attributePaths = "clinica")
	Optional<Medico> findByCpfMedico(String cpfMedico);


	Optional<Medico> findByCrmMedico(String crmMedico);
	
	List<Medico> findByNomeMedicoContainingIgnoreCaseOrderByNomeMedicoAsc(String nomeMedico);

	Page<Medico> findByClinicaIdClinica(Long idClinica, Pageable pageable);

}
