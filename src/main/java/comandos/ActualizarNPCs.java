package comandos;

import mensajeria.PaqueteNPCs;

public class ActualizarNPCs extends ComandosEscucha{
	
	@Override
	public void ejecutar() {
		PaqueteNPCs paqueteNPCs = (PaqueteNPCs) gson.fromJson(cadenaLeida, PaqueteNPCs.class);
		juego.setNPCs( paqueteNPCs.getNPCs() );
	}
	
}