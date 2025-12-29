package br.com.automica.infrastructure.repositories;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.automica.domain.entities.Consulta;
import br.com.automica.domain.projections.ConsultasPorMedicoProjection;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

	@Query("""
			    SELECT c.medico.idMedico AS idMedico,
			           c.medico.nomeMedico AS nomeMedico,
			           COUNT(c.idConsulta) AS quantidade
			    FROM Consulta c
			    WHERE c.dataConsulta BETWEEN :dataInicial AND :dataFinal
			    GROUP BY c.medico.idMedico, c.medico.nomeMedico
			    ORDER BY COUNT(c.idConsulta) DESC
			""")
	List<ConsultasPorMedicoProjection> countConsultasPorMedicoNoPeriodo(@Param("dataInicial") LocalDate dataInicial,
			@Param("dataFinal") LocalDate dataFinal);

	@EntityGraph(attributePaths = { "medico", "paciente" })
	Optional<Consulta> findByIdConsulta(Long id);

	@Query("""
			SELECT c FROM Consulta c
			WHERE c.dataConsulta = :dataConsulta
			AND c.horaConsulta = :horaConsulta
			AND c.medico.idMedico = :idMedico
			ORDER BY c.dataConsulta, horaConsulta
			""")
	Optional<Consulta> findByDataConsultaHoraConsultaMedicoIdMedico(@Param("dataConsulta") LocalDate dataConsulta,
			@Param("horaConsulta") LocalTime horaConsulta, @Param("idMedico") Long idMedico);

	@Query("""
			SELECT c FROM Consulta c
			WHERE c.dataConsulta = :dataConsulta
			AND c.horaConsulta = :horaConsulta
			AND c.paciente.idPaciente = :idPaciente
			ORDER BY c.dataConsulta, horaConsulta
			""")
	Optional<Consulta> findByDataConsultaHoraConsultaPacienteIdPaciente(@Param("dataConsulta") LocalDate dataConsulta,
			@Param("horaConsulta") LocalTime horaConsulta, @Param("idPaciente") Long idPaciente);

	@EntityGraph(attributePaths = { "medico", "paciente" })
	@Query("""
			SELECT c FROM Consulta c
			WHERE c.dataConsulta >= :dataInicio
			AND c.dataConsulta <= :dataFim
			ORDER BY c.dataConsulta, horaConsulta
			""")
	Page<Consulta> findByDataConsultaBetween(@Param("dataInicio") LocalDate dataInicio,
			@Param("dataFim") LocalDate dataFim, Pageable pageable);

	@EntityGraph(attributePaths = { "medico", "paciente" })
	@Query("""
			SELECT c FROM Consulta c
			WHERE c.dataConsulta >= :dataInicio
			AND c.dataConsulta <= :dataFim
			AND c.medico.idMedico = :idMedico
			ORDER BY c.dataConsulta, horaConsulta
			""")
	Page<Consulta> findByDataConsultaBetweenAndMedicoIdMedico(@Param("dataInicio") LocalDate dataInicio,
			@Param("dataFim") LocalDate dataFim, @Param("idMedico") Long idMedico, Pageable pageable);

}
