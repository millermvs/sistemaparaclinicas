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
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "medicos")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Medico {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@EqualsAndHashCode.Include
	private Long idMedico;
	
	@Column(nullable = false, length = 100)
	private String nomeMedico;
	
	@Column(nullable = false, length = 11, unique = true)
	private String cpfMedico;
	
	@Column(nullable = false, length = 20, unique = true)
	private String crmMedico;
	
	@Column(nullable = false, length = 13)
	private String whatsAppMedico;
	
	@Column (nullable = false)
	private Boolean statusAtivo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_clinica", referencedColumnName = "idClinica")
	private Clinica clinica;
	
	@OneToMany(mappedBy = "medico", fetch = FetchType.LAZY)
	private Set<Consulta> consultas = new HashSet<Consulta>();
}
