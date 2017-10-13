package mensajeria;

import java.util.ArrayList;

import dominio.Item;

public class PaqueteNPC {
	private int id;
	private int idMapa;
	private String nombre;
	private int saludTope;
	private int energiaTope;
	private int fuerza;
	private int nivel;
	private ArrayList<Item> items = new ArrayList<Item>();
	
	
	public PaqueteNPC(int id) {
		super();
		this.id = id;
		idMapa = 1;
		nombre = "enemigo";
		saludTope = 100;
		energiaTope = 20;
		fuerza = 50;
		nivel = 1;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdMapa() {
		return idMapa;
	}
	public void setIdMapa(int idMapa) {
		this.idMapa = idMapa;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getSaludTope() {
		return saludTope;
	}
	public void setSaludTope(int saludTope) {
		this.saludTope = saludTope;
	}
	public int getEnergiaTope() {
		return energiaTope;
	}
	public void setEnergiaTope(int energiaTope) {
		this.energiaTope = energiaTope;
	}
	public int getFuerza() {
		return fuerza;
	}
	public void setFuerza(int fuerza) {
		this.fuerza = fuerza;
	}
	public int getNivel() {
		return nivel;
	}
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	public ArrayList<Item> getItems() {
		return items;
	}
	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}

	
}