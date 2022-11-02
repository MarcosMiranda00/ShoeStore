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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyecto.Zapateria.Entidades.Carrito;
import com.proyecto.Zapateria.Entidades.Producto;
import com.proyecto.Zapateria.Interfaces.ICategorias;
import com.proyecto.Zapateria.Interfaces.Icarrito;
import com.proyecto.Zapateria.Interfaces.Iproducto;
import com.proyecto.Zapateria.Interfaces.UsuarioRepo;
import com.sun.el.parser.ParseException;

@Controller
public class ControladroCarrito {
	@Autowired
	private Icarrito repoCar;
	@Autowired
	private Iproducto repoP;
	@Autowired
	private UsuarioRepo repoU;
	@Autowired
	private ICategorias repoCat;
	
	@PostMapping("/GuardarCarrito")
	public String registrar( 
			@RequestParam(value = "nombre")String nombre,
			@RequestParam(value = "Precio")String Precio,
			@RequestParam(value = "imagen")String imagen, 
			@RequestParam(value = "FkProducto2")int FkProducto2, 
			@RequestParam(value = "FkCliente2")int FkCliente2,
			@RequestParam(value = "FkClientep")int FkClientep,
			@RequestParam(value = "cantidad")int cantidad,
			@RequestParam(value = "Total")double Total,
			
			@RequestParam(value = "nombrepp")String nombrepp,
			@RequestParam(value = "preciopp")String preciopp,
			@RequestParam(value = "imagenpp")String imagenpp,
			@RequestParam(value = "estadopp")String estadopp,
			@RequestParam(value = "marcapp")String marcapp,
			@RequestParam(value = "tallapp")String tallapp,
			@RequestParam(value = "colorespp")String colorespp,
			@RequestParam(value = "categoriapp")int categoriapp,
			@RequestParam(value = "cantidadpp")int cantidadpp,
			RedirectAttributes redirectAttrs)throws ParseException{
		
		int res = cantidadpp - cantidad ; 
		
		if (res <= -1) {
			redirectAttrs .addFlashAttribute("mensaje", "ERROR CANTIDADES NEGATIVAS");
				System.out.println( "ERROR CANTIDADES NEGATIVAS" + res);
				return "redirect:/lleva/"+FkProducto2;
		} else {
			
			Producto h = new Producto();
			h.setIdProducto(FkProducto2);
			h.setImagen(imagenpp);
			h.setMarca(marcapp);
			h.setNombre(nombrepp);
			h.setPrecio(preciopp);
			h.setEstado(1);
			h.setFkCategoria(repoCat.findById(categoriapp).get());
			h.setTalla(tallapp);
			h.setColores(colorespp);
			h.setCantidad(res);
			repoP.save(h);
			
			Carrito c = new Carrito();
			c.setNombre(nombre);
			c.setPrecio(Precio);
			c.setEstado("espera");
			c.setImagen(imagen);
			c.setCantidad(cantidad);
			c.setFkProducto2(repoP.findById(FkProducto2).get());
			c.setFkCliente2(repoU.findById(FkCliente2).get());
			c.setFkClienteP(FkClientep);
			c.setTotal(Total);
			repoCar.save(c);
			return "redirect:/compra";
		}
	} 
	
	@PostMapping("/ActualizarCarrito")
	public String actualizar( 
			@RequestParam(value = "idcar")int idcar,
			
			@RequestParam(value = "idproooo")int idpro,
			@RequestParam(value = "nombrepp")String nombrepp,
			@RequestParam(value = "preciopp")String preciopp,
			@RequestParam(value = "imagenpp")String imagenpp,
			@RequestParam(value = "estadopp")String estadopp,
			@RequestParam(value = "marcapp")String marcapp,
			@RequestParam(value = "tallapp")String tallapp,
			@RequestParam(value = "colorespp")String colorespp,
			@RequestParam(value = "categoriapp")int categoriapp,
			@RequestParam(value = "cantidadcc")int cantidadcc,
			@RequestParam(value = "cantidadpp")int cantidadpp,
			RedirectAttributes redirectAttrs)throws ParseException{
		
		int res = cantidadcc + cantidadpp;
		Producto h = new Producto();
		h.setIdProducto(idpro);
		h.setImagen(imagenpp);
		h.setMarca(marcapp);
		h.setNombre(nombrepp);
		h.setPrecio(preciopp);
		h.setEstado(1);
		h.setFkCategoria(repoCat.findById(categoriapp).get());
		h.setTalla(tallapp);
		h.setColores(colorespp);
		h.setCantidad(res);
		repoP.save(h);
		
		repoCar.deleteById(idcar);
		redirectAttrs .addFlashAttribute("mensaje", "PRODUCTO ELIMINADO CORRECTAMENTE");
		return "redirect:/VistaCarrito";
		
	} 
	
	@GetMapping("/VistaCarrito")
	public String listar(Model modelo,Principal principal,RedirectAttributes flash,
	@RequestParam(value="mensaje",required = false)String mensaje) {
    modelo.addAttribute("carr", repoCar.findAll());
    modelo.addAttribute("user", repoU.findByNick(principal.getName()).getId()); 
    
   // int idCar =  repoCar.findAll().size(); 
  //  if (idCar == 0) {
   // 	System.out.println("1");
   // 	modelo.addAttribute("mensaje", "NO HAY PRODUCTOS AGREGADOS");	
   // 	return "VistaCarrito.html";
	//}
    
	return "VistaCarrito.html";
	}
	
	@RequestMapping(value = "/llevardatos2/{id}")
 	public String pasar(@PathVariable("id") int id, Model modelo,Principal principal) {
	   modelo.addAttribute("car", repoCar.findById(id).get());
    return "compra2";
    }
	
	
	
	@RequestMapping(value = "/llevardatos3/{id}")
 	public String pasar3(@PathVariable("id") int id, Model modelo,Principal principal) {
	   modelo.addAttribute("car2", repoCar.findById(id).get());
    return "EliminarCarrito.html";
    }
	
}
