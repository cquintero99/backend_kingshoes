package com.sistema.kingshoes.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="direccion")
public class Direccion implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private Integer usuario_id;
	private String nombre;
	private String apellido;
	private String direccion;
	private String complemento;
	private String barrio;
	@ManyToOne
	@JoinColumn(name="municipio_id")
	private Municipio municipio;
	private String telefono;

}
