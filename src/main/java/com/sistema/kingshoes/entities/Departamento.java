package com.sistema.kingshoes.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name="departamentos")
public class Departamento {
	@Id
	@Column(name="id_departamento")
	private Integer id;
	private String departamento;
	@JsonIgnore
	@OneToMany(mappedBy = "departamento")
	private List<Municipio>municipios;
}
