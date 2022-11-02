package com.proyecto.Zapateria.Interfaces;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.proyecto.Zapateria.Entidades.Producto;

@Repository
public interface Iproducto extends JpaRepository<Producto, Integer> {

	 @Query(value ="select * from Producto p, Categorias c  where p.Fk_Categoria = c.id_Categoria and  c.NOMBREC= ?1",
			 nativeQuery = true)
	 ArrayList<Producto> findAllProductoNative(String nombre);

}
