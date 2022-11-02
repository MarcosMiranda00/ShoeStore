package com.proyecto.Zapateria;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.proyecto.Zapateria.Entidades.Rol;
import com.proyecto.Zapateria.Entidades.Usuario;
import com.proyecto.Zapateria.Interfaces.RolRepo;
import com.proyecto.Zapateria.Interfaces.UsuarioRepo;


@SpringBootApplication
public class ZpateriaApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(ZpateriaApplication.class, args);
		
	}
	@Autowired
	UsuarioRepo repoU;
	@Autowired
	RolRepo repoR;
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public void run(String... args) throws Exception {
		try {
			int regis2 = repoU.findById(1).get().getId();
			if ( regis2 == 1) {	 
				System.out.println(" Ya estas registrado " ); 	
				} 
		} catch (Exception e) {
			Rol r2 = new Rol();
		    r2.setRol("ROLE_USER");
			repoR.save(r2);
			
		Usuario l = new Usuario();
			 l.setNombre1("admin");
			 l.setNombre2("admin2");
		     l.setApellido("adminApe");
		     l.setApellido2("adminApe2");
		     l.setNick("admin@gmail.com");
		     l.setDirecion("Mi casa");
		     l.setDui("12345678");
		     String bcrypt = passwordEncoder.encode("12345");
		     l.setClave(bcrypt);	
		     
		     Rol r = new Rol();
			 r.setRol("ROLE_ADMIN");
			 
		     List<Rol> rol = new ArrayList<Rol>();
		     rol.add(r);
	         l.setRoles(rol);
				
		     repoU.save(l);
		}
		
	}

}
