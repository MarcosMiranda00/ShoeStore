package com.proyecto.Zapateria.Entidades;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Compra")
public class Compra {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCompra;
	
	private Date Fecha;
	private double Total;
	
	private String estado;
	
	@JsonIgnore
	@OneToMany(mappedBy = "FkCompra", cascade = CascadeType.ALL)
	public List<DetalleCompra> FkCompra;

	
	public int getIdCompra() {
		return idCompra;
	}


	public void setIdCompra(int idCompra) {
		this.idCompra = idCompra;
	}


	public Date getFecha() {
		return Fecha;
	}


	public void setFecha(Date fecha) {
		Fecha = fecha;
	}


	public double getTotal() {
		return Total;
	}


	public void setTotal(double total) {
		Total = total;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	public List<DetalleCompra> getFkCompra() {
		return FkCompra;
	}


	public void setFkCompra(List<DetalleCompra> fkCompra) {
		FkCompra = fkCompra;
	}


	public Compra(int idCompra, Date fecha, double total, String estado, List<DetalleCompra> fkCompra) {
		this.idCompra = idCompra;
		Fecha = fecha;
		Total = total;
		this.estado = estado;
		FkCompra = fkCompra;
	}
	
	public Compra(Date fecha, double total, String estado, List<DetalleCompra> fkCompra) {
		Fecha = fecha;
		Total = total;
		this.estado = estado;
		FkCompra = fkCompra;
	}



	public Compra() {
		
	}
	
	
	
}
