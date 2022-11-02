package com.proyecto.Zapateria.Servicios;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.proyecto.Zapateria.Entidades.Rol;
import com.proyecto.Zapateria.Entidades.Usuario;




public class MyUserDetails implements UserDetails{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private Usuario usuario;

	public MyUserDetails(Usuario usuario) {
	this.usuario = usuario;
}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (Rol rol: this.usuario.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(rol.getRol()));
		}
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.usuario.getClave();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.usuario.getNick();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
