package mensajeria;

import java.io.Serializable;

public class PaqueteDios extends Paquete implements Serializable, Cloneable{
	int idPersonaje;
	int idEnemigo;
	boolean god;

	public PaqueteDios() {
	}
	
	public PaqueteDios(int idPj, int idEnem, boolean diosExiste) {
		this.idEnemigo = idEnem;
		this.idPersonaje = idPj;
		this.god = diosExiste;
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

	public boolean isGod() {
		return god;
	}

	public void setGod(boolean god) {
		this.god = god;
	}
}
