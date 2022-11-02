package com.proyecto.Zapateria.Entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Usuario")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String nick; //correo
	String nombre1;
	String nombre2;
	String clave; //contraseña
	private String apellido;
	private String apellido2;
	private String dui;
	private String direcion;
	
	@JsonIgnore 
	@OneToMany(mappedBy = "FkCliente", cascade = CascadeType.ALL)
	public List<DetalleCompra> FkCliente;
	
	@JsonIgnore 
	@OneToMany(mappedBy = "FkCliente2", cascade = CascadeType.ALL)
	public List<Carrito> FkCliente2;
	
	//************************************************************
	
		@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	    @JoinTable( 
	        name = "usuarios_roles", 
	        joinColumns = @JoinColumn(name = "idUsuario", referencedColumnName = "id"), 
	        inverseJoinColumns = @JoinColumn(name = "idRol", referencedColumnName = "id")
	        ) 
	    private List<Rol> roles= new ArrayList<>();
		
		
		//************************************************************
 

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getNombre2() {
		return nombre2;
	}

	public void setNombre2(String nombre2) {
		this.nombre2 = nombre2;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getDui() {
		return dui;
	}

	public void setDui(String dui) {
		this.dui = dui;
	}

	public String getDirecion() {
		return direcion;
	}

	public void setDirecion(String direcion) {
		this.direcion = direcion;
	}

	public List<DetalleCompra> getFkCliente() {
		return FkCliente;
	}

	public void setFkCliente(List<DetalleCompra> fkCliente) {
		FkCliente = fkCliente;
	}

	public List<Carrito> getFkCliente2() {
		return FkCliente2;
	}

	public void setFkCliente2(List<Carrito> fkCliente2) {
		FkCliente2 = fkCliente2;
	}

	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}

	public String getNombre1() {
		return nombre1;
	}

	public void setNombre1(String nombre1) {
		this.nombre1 = nombre1;
	}

	
	
	public Usuario(int id, String nick, String nombre1, String nombre2, String clave, String apellido, String apellido2,
			String dui, String direcion, List<DetalleCompra> fkCliente, List<Carrito> fkCliente2, List<Rol> roles) {
		this.id = id;
		this.nick = nick;
		this.nombre1 = nombre1;
		this.nombre2 = nombre2;
		this.clave = clave;
		this.apellido = apellido;
		this.apellido2 = apellido2;
		this.dui = dui;
		this.direcion = direcion;
		FkCliente = fkCliente;
		FkCliente2 = fkCliente2;
		this.roles = roles;
	}
	
	public Usuario(String nick, String nombre1, String nombre2, String clave, String apellido, String apellido2,
			String dui, String direcion, List<DetalleCompra> fkCliente, List<Carrito> fkCliente2, List<Rol> roles) {
		this.nick = nick;
		this.nombre1 = nombre1;
		this.nombre2 = nombre2;
		this.clave = clave;
		this.apellido = apellido;
		this.apellido2 = apellido2;
		this.dui = dui;
		this.direcion = direcion;
		FkCliente = fkCliente;
		FkCliente2 = fkCliente2;
		this.roles = roles;
	}
	
	public Usuario(String nick, String nombre1, String nombre2, String clave, String apellido, String apellido2,
			String dui, String direcion, List<DetalleCompra> fkCliente, List<Carrito> fkCliente2) {
		this.nick = nick;
		this.nombre1 = nombre1;
		this.nombre2 = nombre2;
		this.clave = clave;
		this.apellido = apellido;
		this.apellido2 = apellido2;
		this.dui = dui;
		this.direcion = direcion;
		FkCliente = fkCliente;
		FkCliente2 = fkCliente2;
	}

	public Usuario() {
	}
	
	
}
