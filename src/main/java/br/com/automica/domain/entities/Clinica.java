package br.com.automica.domain.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "clinicas")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Clinica {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@EqualsAndHashCode.Include
	private Long idClinica;
	
	@Column(nullable = false, length = 100)
	private String nomeClinica;
	
	@Column(nullable = false, unique = true, length = 15)
	private String cnpjClinica;
	
	@OneToMany (mappedBy = "clinica", fetch = FetchType.LAZY)
	private Set<Medico> medicos = new HashSet<Medico>();
}
