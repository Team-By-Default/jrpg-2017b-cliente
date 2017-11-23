package chat;

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
	protected void ejecutar(Juego juego) {
		juego.getPersonaje().setInvisible( !juego.getPersonaje().isInvisible() );
		System.out.println("I cant see you");
	}
}
