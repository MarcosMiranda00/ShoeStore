package com.proyecto.Zapateria.Interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.Zapateria.Entidades.DetalleCompra;

@Repository
public interface IdetalleCompra extends JpaRepository<DetalleCompra, Integer>  {

}
