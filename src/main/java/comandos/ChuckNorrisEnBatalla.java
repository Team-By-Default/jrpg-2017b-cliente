package comandos;

import estados.Estado;
import mensajeria.PaquetePersonajeDominio;

public class ChuckNorrisEnBatalla extends ComandosEscucha {

	@Override
	public void ejecutar() {
		PaquetePersonajeDominio pj = gson.fromJson(cadenaLeida, PaquetePersonajeDominio.class);
		
		if(Estado.getEstado().esEstadoBatalla()) {
			juego.getEstadoBatalla().setEnemigo(pj.getPersonaje());
		}
	}

}
