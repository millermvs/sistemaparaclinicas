package br.com.automica.infrastructure.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.automica.domain.entities.Consulta;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long>{
	
	@EntityGraph(attributePaths = {"medico", "paciente"})
	Optional<Consulta> findByIdConsulta(Long id);

}
