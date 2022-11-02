package com.proyecto.Zapateria.controlador;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.proyecto.Zapateria.Entidades.Producto;
import com.proyecto.Zapateria.Interfaces.ICategorias;
import com.proyecto.Zapateria.Interfaces.Iproducto;
import com.proyecto.Zapateria.Interfaces.UsuarioRepo;
import com.sun.el.parser.ParseException;

@Controller
public class ControladorProducto {
	@Autowired
	private Iproducto repoP;
	@Autowired
	private UsuarioRepo repoU;
	@Autowired
	private ICategorias repcat;

	@GetMapping("/Producto")
	public String listar(Model modelo) {
		modelo.addAttribute("Producto", repoP.findAll());
		modelo.addAttribute("catee", repcat.findAll());
		return "Producto";
	}

	// guardar
	@PostMapping("/CrearProducto")
	public String crear(@RequestParam(value = "marca") String marca, @RequestParam(value = "nombre") String nombre,
			@RequestParam(value = "imagen") MultipartFile imagen, @RequestParam(value = "precio") String precio,
			@RequestParam(value = "categoria") int categoria,
			@RequestParam(value = "talla") String talla, @RequestParam(value = "colores") String colores,
			@RequestParam(value = "cantidad") int cantidad) throws ParseException {
		Producto h = new Producto();
		if (!imagen.isEmpty()) {
			String ruta = "C:\\imagenes";
			try {
				byte[] bytes = imagen.getBytes();
				Path rutaabsoluta = Paths.get(ruta + "//" + imagen.getOriginalFilename());
				Files.write(rutaabsoluta, bytes);
				h.setImagen(imagen.getOriginalFilename());
				h.setMarca(marca);
				h.setNombre(nombre);
				h.setPrecio(precio);
				h.setEstado(1);
				h.setFkCategoria(repcat.findById(categoria).get());
				h.setTalla(talla);
				h.setColores(colores);
				h.setCantidad(cantidad);
				repoP.save(h);

			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		return "redirect:/Producto";
	}

	@GetMapping("/carrito")
	public String listar2(Model modelo) {
		return "carrito.html";
	}

	@RequestMapping(value = "/llevar/{id}")
	public ResponseEntity<Producto> show(@PathVariable(value = "id") int id) {
		Producto v = repoP.findById(id).get();
		System.out.println(v);
		return ResponseEntity.ok(v);
	}
	
	@RequestMapping(value = "/llevarPro/{id}")
	public String pasarP(@PathVariable("id") int id, Model modelo) {
		modelo.addAttribute("proo", repoP.findById(id).get());
		modelo.addAttribute("catee2", repcat.findAll());
		return "ProductoActu";
	}

	@RequestMapping(value = "/lleva/{id}")
	public String pasar(@PathVariable("id") int id, Model modelo, Principal principal) {
		modelo.addAttribute("prod", repoP.findById(id).get());
		modelo.addAttribute("cliend", repoU.findByNick(principal.getName()).getId());
		return "carrito";
	}

	// actualizar
	@RequestMapping(value = "/actualizar", method = RequestMethod.POST)
	public String actualizar(@RequestParam(value = "id") int id, @RequestParam(value = "marca") String marca,
			@RequestParam(value = "nombre") String nombre, @RequestParam(value = "imagen") MultipartFile imagen,
			@RequestParam(value = "precio") String precio, @RequestParam(value = "categoria") int categoria,
			@RequestParam(value = "talla") String talla, @RequestParam(value = "colores") String colores,
			@RequestParam(value = "cantidad") int cantidad)
			throws ParseException {
		Producto h = new Producto();
		if (!imagen.isEmpty()) {
			String ruta = "C:\\imagenes";
			try {
				byte[] bytes = imagen.getBytes();
				Path rutaabsoluta = Paths.get(ruta + "//" + imagen.getOriginalFilename());
				Files.write(rutaabsoluta, bytes);
				h.setIdProducto(id);
				h.setImagen(imagen.getOriginalFilename());
				h.setMarca(marca);
				h.setNombre(nombre);
				h.setPrecio(precio);
				h.setEstado(1);
				h.setFkCategoria(repcat.findById(categoria).get());
				h.setTalla(talla);
				h.setColores(colores);
				h.setCantidad(cantidad);
				repoP.save(h);

			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		return "redirect:/Producto";
	}

	// ELIMINAR DATOS
	@GetMapping("/BorrarProducto/{id}")
	public String borrar(@PathVariable("id") int id) {
		repoP.deleteById(id);
		return "redirect:/Producto";
	}
}
