package chat;

import juego.Juego;

public class GodModeHandler extends TrickHandler {
	
	public final static String miComando = "iddqd";
	
	public GodModeHandler(TrickHandler sucesor) {
		super(sucesor);
	}
	
	@Override
	public boolean puedoSoportarlo(String comando) {
		return this.miComando.equals(comando);
	}
	@Override
	protected void ejecutar(Juego juego) {
		
		if(juego.getPersonaje().isDios())
			juego.getPersonaje().setDios(false);
		else
			juego.getPersonaje().setDios(true);
		System.out.println(juego.getPersonaje().isDios()); 
		
	}
}
