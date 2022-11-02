package com.proyecto.Zapateria.Entidades;


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
@Table(name = "Categorias")
public class Categorias {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCategoria;
	private String nombreC;
	
	@JsonIgnore
	@OneToMany(mappedBy = "FkCategoria", cascade = CascadeType.ALL)
	public List<Producto> FkCategoria;

	

	public int getIdCategoria() {
		return idCategoria;
	}



	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}



	public String getNombreC() {
		return nombreC;
	}



	public void setNombreC(String nombreC) {
		this.nombreC = nombreC;
	}



	public List<Producto> getFkCategoria() {
		return FkCategoria;
	}



	public void setFkCategoria(List<Producto> fkCategoria) {
		FkCategoria = fkCategoria;
	}



	public Categorias(int idCategoria, String nombreC, List<Producto> fkCategoria) {
		this.idCategoria = idCategoria;
		this.nombreC = nombreC;
		FkCategoria = fkCategoria;
	}

	public Categorias(String nombreC, List<Producto> fkCategoria) {
		this.nombreC = nombreC;
		FkCategoria = fkCategoria;
	}

	public Categorias() {

	}

	
}
