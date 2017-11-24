package mensajeria;

import java.io.Serializable;

import dominio.Personaje;

public class PaquetePersonajeDominio extends Paquete implements Serializable, Cloneable{
	int idPersonaje;
	int idEnemigo;
	Personaje personaje;
	
	public PaquetePersonajeDominio(int idPj, int idEnem, Personaje pj) {
		this.idEnemigo = idEnem;
		this.idPersonaje = idPj;
		this.personaje = pj;
	}
	
	public int getIdPersonaje() {
		return idPersonaje;
	}
	public void setIdPersonaje(int idPersonaje) {
		this.idPersonaje = idPersonaje;
	}
	public int getIdEnemigo() {
		return idEnemigo;
	}
	public void setIdEnemigo(int idEnemigo) {
		this.idEnemigo = idEnemigo;
	}
	public Personaje getPersonaje() {
		return personaje;
	}
	public void setPersonaje(Personaje personaje) {
		this.personaje = personaje;
	}
}
