package br.com.automica.domain.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "pacientes")
public class Paciente {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long idPaciente;

	@Column(nullable = false, length = 100)
	private String nomePaciente;

	@Column(nullable = false, length = 11, unique = true)
	private String cpfPaciente;

	@Column(nullable = false, length = 13)
	private String whatsAppPaciente;

	@OneToMany(mappedBy = "paciente", fetch = FetchType.LAZY)
	private Set<Consulta> consultas = new HashSet<Consulta>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_clinica", referencedColumnName = "idClinica")
	private Clinica clinica;
}
