package chat;

import juego.Juego;

public class GodModeHandler extends TrickHandler {
	
	public final static String miComando = "iddqd";
	public final static String miComando2 = "manners maketh man";
	
	public GodModeHandler(TrickHandler sucesor) {
		super(sucesor);
	}
	
	@Override
	public boolean puedoSoportarlo(String comando) {
		return this.miComando.equals(comando) || this.miComando2.equals(comando);
	}
	
	@Override
	protected void ejecutar(Juego juego) {
		
		juego.getPersonaje().setDios( !juego.getPersonaje().isDios() );
		System.out.println(juego.getPersonaje().isDios()); 
		
	}
}
