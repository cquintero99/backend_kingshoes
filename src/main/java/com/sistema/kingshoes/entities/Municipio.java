package com.sistema.kingshoes.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="municipios")
public class Municipio {
	@Id
	@Column(name="id_municipio")
	private Integer id;
	private String municipio;
	private String estado;
	@ManyToOne
	@JoinColumn(name="departamento_id")
	private Departamento departamento;

}
