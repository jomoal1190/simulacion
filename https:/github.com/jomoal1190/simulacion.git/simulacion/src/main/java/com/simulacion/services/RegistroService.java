package com.simulacion.services;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.simulacion.models.Registro;

public interface RegistroService extends JpaRepository<Registro, Serializable>{

	@Query("select new map(year(r.fechaCreacion) as anio, sum(r.cantidad) as total) from Registro r "
			+ "where year(r.fechaCreacion)=year(r.fechaCreacion) group by year(r.fechaCreacion) order by year(r.fechaCreacion) asc") 
	List<Map> findByParametrosAll();
}
