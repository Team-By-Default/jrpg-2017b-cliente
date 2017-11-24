package chat;

import javax.swing.JTextArea;

import juego.Juego;
import mensajeria.PaquetePersonaje;

public class InvisibleHandler extends TrickHandler {
	public final static String miComando = "war aint what it used to be";
	public final static String miComando2 = "invisible";

	public InvisibleHandler(TrickHandler sucesor) {
		super(sucesor);
	}

	@Override
	public boolean puedoSoportarlo(String comando) {
		return this.miComando.equals(comando) || this.miComando2.equals(comando);
	}
	
	@Override
	protected void ejecutar(Juego juego,JTextArea chat) {
		
		juego.getCliente().getPaquetePersonaje().setInvisible(
				!juego.getCliente().getPaquetePersonaje().isInvisible());
		
		if(juego.getCliente().getPaquetePersonaje().isInvisible())
			chat.append("Truco activado. Ahora el personaje es invisible.\n");
		else
			chat.append("Truco desactivado. Ahora el personaje vuelve a ser visible.\n");
	}
}
