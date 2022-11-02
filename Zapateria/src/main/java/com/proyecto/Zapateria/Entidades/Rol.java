package com.proyecto.Zapateria.Entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Rol")
public class Rol {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
int id;
String rol;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getRol() {
	return rol;
}
public void setRol(String rol) {
	this.rol = rol;
}


}
