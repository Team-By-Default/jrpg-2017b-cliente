package chat;

import juego.Juego;
import mensajeria.PaquetePersonaje;

public class InvisibleHandler extends TrickHandler {
	public final static String miComando = "invisible";

	public InvisibleHandler(TrickHandler sucesor) {
		super(sucesor);
	}

	@Override
	public boolean puedoSoportarlo(String comando) {
		return this.miComando.equals(comando);
	}
	
	@Override
	protected void ejecutar(Juego juego) {
		PaquetePersonaje pj = juego.getCliente().getPaquetePersonaje();
		pj.setInvisible(!pj.isInvisible());
		juego.getCliente().setPaquetePersonaje(pj);
		
		System.out.println("I cant see you");
	}
}
