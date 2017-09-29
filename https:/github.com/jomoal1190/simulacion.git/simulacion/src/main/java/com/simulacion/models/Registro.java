package com.simulacion.models;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="registro")
public class Registro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id_regitro")
	private Long id;
	@Column(name="fecha_creacion")
	@Type(type="date")
	private Date fechaCreacion;
	@Column(name="cantidad")
	private Integer cantidad;
	
	
	public Registro() {}
	
	
	public Registro(Long id, Date fechaCreacion, Integer cantidad) {
		super();
		this.id = id;
		this.fechaCreacion = fechaCreacion;
		this.cantidad = cantidad;
	}

	

	

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
	
	
	

}
