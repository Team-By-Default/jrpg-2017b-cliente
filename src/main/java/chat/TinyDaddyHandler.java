package chat;

import juego.Juego;

public class TinyDaddyHandler extends TrickHandler{
	public final static String miComando = "tinydaddy";
	
	public TinyDaddyHandler(TrickHandler sucesor) {
		super(sucesor);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean puedoSoportarlo(String comando) {
		return this.miComando.equals(comando);
	}

	@Override
	protected void ejecutar(Juego juego) {
		// TODO Auto-generated method stub
		
	}
}
