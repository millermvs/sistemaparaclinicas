package br.com.automica.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "medicos")
public class Medico {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long idMedico;
	
	@Column(nullable = false, length = 100)
	private String nomeMedico;
	
	@Column(nullable = false, length = 11, unique = true)
	private String cpfMedico;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_clinica", referencedColumnName = "idClinica")
	private Clinica clinica;
}
