package comandos;

import mensajeria.PaqueteDeMovimientos;

public class MovimientoNPC extends ComandosEscucha{
	
	
	@Override
	public void ejecutar() {
		
		PaqueteDeMovimientos pdm = (PaqueteDeMovimientos) gson.fromJson(cadenaLeida,PaqueteDeMovimientos.class);
		juego.setUbicacionNPCs(pdm.getPersonajes());
		
	}
}