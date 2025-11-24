package br.com.automica.infrastructure.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.automica.domain.entities.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long>{
	
	Optional<Paciente> findByIdPaciente(Long id);
	
	Optional<Paciente> findByCpfPaciente(String cpf);

}
