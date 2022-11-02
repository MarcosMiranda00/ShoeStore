package com.proyecto.Zapateria.Interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.Zapateria.Entidades.Usuario;



public interface UsuarioRepo extends JpaRepository<Usuario, Integer>{
public Usuario findByNick(String nick);
}
