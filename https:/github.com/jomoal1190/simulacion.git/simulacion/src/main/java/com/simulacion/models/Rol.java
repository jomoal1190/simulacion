package com.simulacion.models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name="rol")
public class Rol {
	
	@Id
	@Column(name="codigo_rol")
	private Integer codigoRol;
	
	@Column(name="descripcion")
	private String descripcion;

	 @OneToMany(fetch = FetchType.EAGER, mappedBy = "roles")
	 @JsonManagedReference
	private Set<UserPermission> userrole;
	
	
	public Integer getCodigoRol() {
		return codigoRol;
	}

	public void setCodigoRol(Integer codigoRol) {
		this.codigoRol = codigoRol;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	

}