package br.com.automica.domain.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import br.com.automica.domain.enums.StatusConsulta;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "consultas")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Consulta {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@EqualsAndHashCode.Include
	private Long idConsulta;

	@Column(nullable = false)
	private LocalDate dataConsulta;

	@Column(nullable = false)
	private LocalTime horaConsulta;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private StatusConsulta status;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_medico", referencedColumnName = "idMedico")
	private Medico medico;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_paciente", referencedColumnName = "idPaciente")
	private Paciente paciente;

}
