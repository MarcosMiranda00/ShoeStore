package com.proyecto.Zapateria.Interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.proyecto.Zapateria.Entidades.Compra;

@Repository
public interface Icompra extends JpaRepository<Compra, Integer>{
	 @Modifying
	 @Query(value = "UPDATE compra c SET c.estado = 'REALIZADA' where d.Id_Compra = ?1", nativeQuery = true)
	 int updateCompraSerEstadoForNameNative(int developerid);
}
