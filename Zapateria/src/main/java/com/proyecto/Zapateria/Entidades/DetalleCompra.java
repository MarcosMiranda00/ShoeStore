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
@Table(name = "DetalleCompra")
public class DetalleCompra {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idDetalleCompra;
	
	private String nombreProd;
	private String Precio;
	private int Cantidad;
	
	@JoinColumn(name = "FkProducto")
	@ManyToOne(fetch = FetchType.EAGER)
    private Producto FkProducto;
	
	@JoinColumn(name = "FkCompra")
	@ManyToOne(fetch = FetchType.LAZY)
    private Compra FkCompra;

	@JoinColumn(name = "FkCliente")
	@ManyToOne(fetch = FetchType.EAGER)
    private Usuario FkCliente;
	
	
	public int getIdDetalleCompra() {
		return idDetalleCompra;
	}


	public void setIdDetalleCompra(int idDetalleCompra) {
		this.idDetalleCompra = idDetalleCompra;
	}


	public String getNombreProd() {
		return nombreProd;
	}


	public void setNombreProd(String nombreProd) {
		this.nombreProd = nombreProd;
	}


	public String getPrecio() {
		return Precio;
	}


	public void setPrecio(String precio) {
		Precio = precio;
	}


	public int getCantidad() {
		return Cantidad;
	}


	public void setCantidad(int cantidad) {
		Cantidad = cantidad;
	}


	public Producto getFkProducto() {
		return FkProducto;
	}


	public void setFkProducto(Producto fkProducto) {
		FkProducto = fkProducto;
	}


	public Compra getFkCompra() {
		return FkCompra;
	}


	public void setFkCompra(Compra fkCompra) {
		FkCompra = fkCompra;
	}


	public Usuario getFkCliente() {
		return FkCliente;
	}


	public void setFkCliente(Usuario fkCliente) {
		FkCliente = fkCliente;
	}


	public DetalleCompra(int idDetalleCompra, String nombreProd, String precio, int cantidad, Producto fkProducto,
			Compra fkCompra, Usuario fkCliente) {
		this.idDetalleCompra = idDetalleCompra;
		this.nombreProd = nombreProd;
		Precio = precio;
		Cantidad = cantidad;
		FkProducto = fkProducto;
		FkCompra = fkCompra;
		FkCliente = fkCliente;
	}

	public DetalleCompra( String nombreProd, String precio, int cantidad, Producto fkProducto,
			Compra fkCompra, Usuario fkCliente) {
		this.nombreProd = nombreProd;
		Precio = precio;
		Cantidad = cantidad;
		FkProducto = fkProducto;
		FkCompra = fkCompra;
		FkCliente = fkCliente;
	}
	public DetalleCompra() {
		
	}
	
	
}
