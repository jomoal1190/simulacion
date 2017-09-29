package com.simulacion.services;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.simulacion.models.Rol;
import com.simulacion.models.User;



public interface UserRepository extends JpaRepository<User, Long> {

	public final static String GET_ROLES_BY_USER = "select  rol.codigo_rol, rol.descripcion from users us inner join userroles uroles on us.username = uroles.username inner join rol rol on uroles.roles = rol.codigo_rol where us.username = :username";
	
	User findOneByUsername(String username);
	

	@Query(value = GET_ROLES_BY_USER , nativeQuery = true)
	
	List<Rol> listRoles(@Param("username") String username);
	
	
	
}
