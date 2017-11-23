package chat;

import javax.swing.JTextArea;

import juego.Juego;
import mensajeria.PaquetePersonaje;

public class InvisibleHandler extends TrickHandler {
	public final static String miComando = "invisible";

	public InvisibleHandler(TrickHandler sucesor) {
		super(sucesor);
	}

	@Override
	public boolean puedoSoportarlo(String comando) {
		return miComando.equals(comando);
	}
	
	@Override
	protected void ejecutar(Juego juego,JTextArea chat) {
		PaquetePersonaje pj = juego.getCliente().getPaquetePersonaje();
		pj.setInvisible(!pj.isInvisible());
		juego.getCliente().setPaquetePersonaje(pj);
		if(pj.isInvisible())
			chat.append("Truco activado. Ahora el personaje es invisible.\n");
		else
			chat.append("Truco desactivado. Ahora el personaje vuelve a ser visible.\n");
		
	}
}
