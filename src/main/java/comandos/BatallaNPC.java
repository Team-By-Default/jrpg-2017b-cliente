package comandos;

import estados.Estado;
import estados.EstadoBatallaNPC;
import mensajeria.PaqueteBatallaNPC;

public class BatallaNPC extends ComandosEscucha{

	@Override
	public void ejecutar() {
		PaqueteBatallaNPC paqueteBatalla = (PaqueteBatallaNPC) gson.fromJson(cadenaLeida, PaqueteBatallaNPC.class);
		juego.getPersonaje().setEstado(Estado.estadoBatallaNPC);
		Estado.setEstado(null);
		juego.setEstadoBatallaNPC(new EstadoBatallaNPC(juego, paqueteBatalla));
		Estado.setEstado(juego.getEstadoBatallaNPC());
	}

}
