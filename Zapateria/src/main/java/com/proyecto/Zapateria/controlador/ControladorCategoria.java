package com.proyecto.Zapateria.controlador;


import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.proyecto.Zapateria.Entidades.Categorias;
import com.proyecto.Zapateria.Interfaces.ICategorias;
import com.sun.el.parser.ParseException;

@Controller
public class ControladorCategoria {
	@Autowired
	private ICategorias repoca;

	@GetMapping("/categorias")
	public String categoria(Model modelo) {
		modelo.addAttribute("catEE", repoca.findAll());
		return "categorias";
	}
	
	@GetMapping("/NuevaCategoria")
	public String Ncategoria(Model modelo) {
		return "categoriasNueva";
	}

	@GetMapping("/BorrarCate/{id}")
	public String borrar(@PathVariable("id") int id) {
		repoca.deleteById(id);
		return "redirect:/categorias";
	}

	@RequestMapping(value = "/llevarCat/{id}")
	public String pasar(@PathVariable("id") int id, Model modelo, Principal principal) {
		modelo.addAttribute("cate", repoca.findById(id).get());
		return "categoriaActulizar";
	}

	@PostMapping("/CrearCatego")
	public String crear(@RequestParam(value = "nombre") String nombre) throws ParseException {
		Categorias ca = new Categorias();
		try {
			ca.setNombreC(nombre);
			repoca.save(ca);

		} catch (Exception e) {
			// TODO: handle exception
		}

		return "redirect:/categorias";
	}
	
	@PostMapping("/ActualizarCate")
	public String Actualizar(@RequestParam(value = "id") int id,
			@RequestParam(value = "nombre") String nombre) throws ParseException {
		Categorias ca = new Categorias();
		try {
			ca.setIdCategoria(id);
			ca.setNombreC(nombre);
			repoca.save(ca);

		} catch (Exception e) {
			// TODO: handle exception
		}

		return "redirect:/categorias";
	}
}
