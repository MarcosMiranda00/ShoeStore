package com.proyecto.Zapateria.Entidades;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "Producto")
public class Producto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idProducto;
	private String nombre;
	private String Precio;
	private String imagen;
	private int estado;
	private String marca;
	
	private String talla;
	private String colores;
	
	private int cantidad;
	
	@JoinColumn(name = "FkCategoria")
	@ManyToOne(fetch = FetchType.EAGER)
    private Categorias FkCategoria;

	@JsonIgnore
	@OneToMany(mappedBy = "FkProducto", cascade = CascadeType.ALL)
	public List<DetalleCompra> FkProducto;
	
	@JsonIgnore
	@OneToMany(mappedBy = "FkProducto2", cascade = CascadeType.ALL)
	public List<Carrito> FkProducto2;

	
	public int getIdProducto() {
		return idProducto;
	}


	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getPrecio() {
		return Precio;
	}


	public void setPrecio(String precio) {
		Precio = precio;
	}


	public String getImagen() {
		return imagen;
	}


	public void setImagen(String imagen) {
		this.imagen = imagen;
	}


	public int getEstado() {
		return estado;
	}


	public void setEstado(int estado) {
		this.estado = estado;
	}


	public String getMarca() {
		return marca;
	}


	public void setMarca(String marca) {
		this.marca = marca;
	}


	public String getTalla() {
		return talla;
	}


	public void setTalla(String talla) {
		this.talla = talla;
	}


	public String getColores() {
		return colores;
	}


	public void setColores(String colores) {
		this.colores = colores;
	}


	public Categorias getFkCategoria() {
		return FkCategoria;
	}


	public void setFkCategoria(Categorias fkCategoria) {
		FkCategoria = fkCategoria;
	}


	public List<DetalleCompra> getFkProducto() {
		return FkProducto;
	}


	public void setFkProducto(List<DetalleCompra> fkProducto) {
		FkProducto = fkProducto;
	}


	public List<Carrito> getFkProducto2() {
		return FkProducto2;
	}


	public void setFkProducto2(List<Carrito> fkProducto2) {
		FkProducto2 = fkProducto2;
	}

	public int getCantidad() {
		return cantidad;
	}


	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}


	public Producto(int idProducto, String nombre, String precio, String imagen, int estado, String marca, String talla,
			String colores, int cantidad, Categorias fkCategoria, List<DetalleCompra> fkProducto,
			List<Carrito> fkProducto2) {
		this.idProducto = idProducto;
		this.nombre = nombre;
		Precio = precio;
		this.imagen = imagen;
		this.estado = estado;
		this.marca = marca;
		this.talla = talla;
		this.colores = colores;
		this.cantidad = cantidad;
		FkCategoria = fkCategoria;
		FkProducto = fkProducto;
		FkProducto2 = fkProducto2;
	}
	
	
	public Producto( String nombre, String precio, String imagen, int estado, String marca, String talla,
			String colores, int cantidad, Categorias fkCategoria, List<DetalleCompra> fkProducto,
			List<Carrito> fkProducto2) {
		this.nombre = nombre;
		Precio = precio;
		this.imagen = imagen;
		this.estado = estado;
		this.marca = marca;
		this.talla = talla;
		this.colores = colores;
		this.cantidad = cantidad;
		FkCategoria = fkCategoria;
		FkProducto = fkProducto;
		FkProducto2 = fkProducto2;
	}


	public Producto( ) {

	}
	

}
