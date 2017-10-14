package comandos;

import mensajeria.PaqueteNPC;
import mensajeria.PaqueteNPCs;
import mensajeria.PaquetePersonaje;

public class ActualizarNPCs extends ComandosEscucha{
	@Override
	public void ejecutar() {
		PaqueteNPCs paqueteNPCs = (PaqueteNPCs) gson.fromJson(cadenaLeida, PaqueteNPCs.class);

		//juego.getPersonajesConectados().remove(paqueteDeNPC.getId());
		juego.setNPCs( paqueteNPCs.getNPCs() );
		
		/*
		if (juego.getPersonaje().getId() == paquetePersonaje.getId()) {
			juego.actualizarPersonaje();
			juego.getEstadoJuego().actualizarPersonaje();
			juego.getCliente().actualizarItems(paquetePersonaje);
			juego.getCliente().actualizarPersonaje(juego.getPersonajesConectados().get(paquetePersonaje.getId()));
		} */

	}
}