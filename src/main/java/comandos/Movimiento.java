package comandos;

import mensajeria.PaqueteDeMovimientos;

public class Movimiento extends ComandosEscucha{

	@Override
	public void ejecutar() {
		PaqueteDeMovimientos pdm = (PaqueteDeMovimientos) gson.fromJson(cadenaLeida,PaqueteDeMovimientos.class);
		juego.setUbicacionPersonajes(pdm.getPersonajes());
		
	}

}
