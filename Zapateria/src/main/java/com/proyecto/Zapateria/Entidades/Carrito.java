package com.proyecto.Zapateria.Entidades;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Carrito")
public class Carrito {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCarrito;
	private String nombre;
	private String precio;
	private String Estado;
	private String imagen;
	private int Cantidad;
	private double Total;
	
	@JoinColumn(name = "FkProducto2")
	@ManyToOne(fetch = FetchType.EAGER)
    private Producto FkProducto2;

	@JoinColumn(name = "FkCliente2")
	@ManyToOne(fetch = FetchType.EAGER)
    private Usuario FkCliente2;

	private int FkClienteP;
   

   




	public int getIdCarrito() {
		return idCarrito;
	}







	public void setIdCarrito(int idCarrito) {
		this.idCarrito = idCarrito;
	}







	public String getNombre() {
		return nombre;
	}







	public void setNombre(String nombre) {
		this.nombre = nombre;
	}







	public String getPrecio() {
		return precio;
	}







	public void setPrecio(String precio) {
		this.precio = precio;
	}







	public String getEstado() {
		return Estado;
	}







	public void setEstado(String estado) {
		Estado = estado;
	}







	public String getImagen() {
		return imagen;
	}







	public void setImagen(String imagen) {
		this.imagen = imagen;
	}







	public int getCantidad() {
		return Cantidad;
	}







	public void setCantidad(int cantidad) {
		Cantidad = cantidad;
	}







	public double getTotal() {
		return Total;
	}







	public void setTotal(double total) {
		Total = total;
	}







	public Producto getFkProducto2() {
		return FkProducto2;
	}







	public void setFkProducto2(Producto fkProducto2) {
		FkProducto2 = fkProducto2;
	}







	public Usuario getFkCliente2() {
		return FkCliente2;
	}







	public void setFkCliente2(Usuario fkCliente2) {
		FkCliente2 = fkCliente2;
	}







	public int getFkClienteP() {
		return FkClienteP;
	}







	public void setFkClienteP(int fkClienteP) {
		FkClienteP = fkClienteP;
	}







	public Carrito(int idCarrito, String nombre, String precio, String estado, String imagen, int cantidad,
			double total, Producto fkProducto2, Usuario fkCliente2, int fkClienteP) {
		this.idCarrito = idCarrito;
		this.nombre = nombre;
		this.precio = precio;
		Estado = estado;
		this.imagen = imagen;
		Cantidad = cantidad;
		Total = total;
		FkProducto2 = fkProducto2;
		FkCliente2 = fkCliente2;
		FkClienteP = fkClienteP;
	}

	public Carrito(String nombre, String precio, String estado, String imagen, int cantidad,
			double total, Producto fkProducto2, Usuario fkCliente2, int fkClienteP) {
		this.nombre = nombre;
		this.precio = precio;
		Estado = estado;
		this.imagen = imagen;
		Cantidad = cantidad;
		Total = total;
		FkProducto2 = fkProducto2;
		FkCliente2 = fkCliente2;
		FkClienteP = fkClienteP;
	}





	public Carrito(int fkClienteP) {
		FkClienteP = fkClienteP;
	}







	public Carrito() {

	}
	
	
}
