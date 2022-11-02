package com.proyecto.Zapateria.controlador;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proyecto.Zapateria.Entidades.Compra;
import com.proyecto.Zapateria.Interfaces.Icompra;
import com.proyecto.Zapateria.Interfaces.IdetalleCompra;
import com.proyecto.Zapateria.Interfaces.UsuarioRepo;

@Controller
public class ControladorDetallecompra {
	@Autowired
	private Icompra repoC;
	@Autowired
	private IdetalleCompra repoD;
	@Autowired
	private UsuarioRepo repoU;
	
	@GetMapping("/VistaDetalle")
	public String listar(Model modelo, Principal principal) {
    modelo.addAttribute("detalle", repoD.findAll());
    modelo.addAttribute("user", repoU.findByNick(principal.getName()).getId());
	return "VistaCompras.html";
   }
	@GetMapping("/VistaDetalle2")
	public String lista2(Model modelo, Principal principal) {
    modelo.addAttribute("detalle", repoD.findAll());
    modelo.addAttribute("user", repoU.findByNick(principal.getName()).getId());
	return "VistaCompras2.html";
   }
	
	@RequestMapping(value = "/llevars/{id}")
	public ResponseEntity<Compra> show(@PathVariable(value = "id")int id,Model modelo) {
		Compra c = repoC.findById(id).get();	
		modelo.addAttribute("deta", c);
		return ResponseEntity.ok(c);
	}
	
}
