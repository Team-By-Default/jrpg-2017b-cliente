package chat;

import juego.Juego;

public class BigDaddyHandler extends TrickHandler {
	public final static String miComando = "bigdaddy";
	
	public BigDaddyHandler(TrickHandler sucesor) {
		super(sucesor);
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
