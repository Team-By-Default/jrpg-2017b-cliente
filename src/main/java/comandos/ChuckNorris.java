package comandos;

import estados.Estado;
import mensajeria.PaqueteDios;

public class ChuckNorris extends ComandosEscucha {

	@Override
	public void ejecutar() {
		PaqueteDios pj = gson.fromJson(cadenaLeida, PaqueteDios.class);
		
		if(Estado.getEstado().esEstadoBatalla()) {
			juego.getEstadoBatalla().getEnemigo().setGod(pj.isGod());
		}
	}

}
