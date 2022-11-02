package com.proyecto.Zapateria.controlador;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyecto.Zapateria.Entidades.Rol;
import com.proyecto.Zapateria.Entidades.Usuario;
import com.proyecto.Zapateria.Interfaces.RolRepo;
import com.proyecto.Zapateria.Interfaces.UsuarioRepo;
import com.sun.el.parser.ParseException;

@Controller
public class LoginControler {
	@Autowired
	UsuarioRepo repoU;
	@Autowired
	RolRepo repoR;
	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@RequestMapping("/login")
	public String login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, Model model, Principal principal,
			RedirectAttributes flash) {

		if (principal != null) {
			flash.addFlashAttribute("info", "La sesión esta activa");
			/*
			 * Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			 * System.out.println(auth.getName());
			 * 
			 * model.addAttribute("cliend",auth.getName());
			 */
			return "redirect:/menu";
		}

		if (error != null) {
			model.addAttribute("error", "Los datos ingresados son incorrectos");
		}
		if (logout != null) {
			model.addAttribute("salida", "La sesión ha finalizado con exito");
		}

		return "Login";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";
	}

	@GetMapping("/registrar")
	public String listar(Model modelo) {
		return "RegistroUsuario.html";
	}

	@GetMapping("/perfil")
	public String listar3(Model modelo, Principal principal) {
		modelo.addAttribute("cliend", repoU.findByNick(principal.getName()));
		return "Perfil.html";
	}

	@RequestMapping(value = "/llevarUsu")
	public String pasarP(Model modelo , Principal principal) {
		modelo.addAttribute("usu", repoU.findByNick(principal.getName()));
		return "EditarPerfil.html";
	}

	@GetMapping("/login2")
	public String listar2(Model modelo) {
		return "login2.html";
	}

	@RequestMapping(value = "/CrearRegistro", method = RequestMethod.POST)
	public String crear(@RequestParam(value = "Pnombre") String nombre, @RequestParam(value = "Snombre") String Nombre2,
			@RequestParam(value = "Papellido") String apellido, @RequestParam(value = "Sapellido") String Apellido2,
			@RequestParam(value = "correo") String correo, @RequestParam(value = "password") String password,
			@RequestParam(value = "dui") String dui, @RequestParam(value = "dire") String Direcion)
			throws ParseException {

		try {
			Usuario l = new Usuario();
			l.setNombre1(nombre);
			l.setNombre2(Nombre2);
			l.setApellido(apellido);
			l.setApellido2(Apellido2);
			l.setNick(correo);
			String bcrypt = passwordEncoder.encode(password);
			l.setClave(bcrypt);
			l.setDui(dui);
			l.setDirecion(Direcion);
			Rol r = new Rol();
			r.setRol("ROLE_USER");

			List<Rol> rol = new ArrayList<Rol>();
			rol.add(r);
			l.setRoles(rol);
			repoU.save(l);

			return "redirect:/login";
		} catch (Exception e) {
			return "eorr" + e;
		}

	}
	
	@RequestMapping(value = "/ActualizarUser", method = RequestMethod.POST)
	public String Actualizar(
			@RequestParam(value = "idU") int idU, 
			@RequestParam(value = "Pnombre") String nombre, 
			@RequestParam(value = "Snombre") String Nombre2,
			@RequestParam(value = "Papellido") String apellido, 
			@RequestParam(value = "Sapellido") String Apellido2,
			@RequestParam(value = "correo") String correo, 
			@RequestParam(value = "password") String password,
			@RequestParam(value = "pass") String pass,
			@RequestParam(value = "dui") String dui,
			@RequestParam(value = "dire") String Direcion,  Principal principal)
			throws ParseException {

		if(password.equals("") || password.equals(null) || password == "" || password == null) {
			Usuario l = new Usuario();
			l.setId(idU);
			l.setNombre1(nombre);
			l.setNombre2(Nombre2);
			l.setApellido(apellido);
			l.setApellido2(Apellido2);
			l.setNick(correo);
			l.setClave(pass);
			l.setDui(dui);
			l.setDirecion(Direcion);
			l.setRoles(repoU.findByNick(principal.getName()).getRoles());
			repoU.save(l);
			return "redirect:/logout";
			
		}else {
			try {
				Usuario l = new Usuario();
				l.setId(idU);
				l.setNombre1(nombre);
				l.setNombre2(Nombre2);
				l.setApellido(apellido);
				l.setApellido2(Apellido2);
				l.setNick(correo);
				String bcrypt = passwordEncoder.encode(password);
				l.setClave(bcrypt);
				l.setDui(dui);
				l.setDirecion(Direcion);
				l.setRoles(repoU.findByNick(principal.getName()).getRoles());
				repoU.save(l);
				return "redirect:/logout";
			} catch (Exception e) {
				return "eorr" + e;
			}
		}
	}
}
