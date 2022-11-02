package com.proyecto.Zapateria.Servicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.Zapateria.Entidades.Usuario;
import com.proyecto.Zapateria.Interfaces.UsuarioRepo;






@Service
@Transactional(readOnly = true)
public class MyUserDateilsService implements UserDetailsService{
@Autowired
UsuarioRepo repo;
@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = repo.findByNick(username);
		MyUserDetails userDatails;
		if (usuario == null) {
			throw new UsernameNotFoundException("No se encontro el usuario" + username);
		}else {
			userDatails = new MyUserDetails(usuario);
		}
		return userDatails;
	}

}
