package chat;

import javax.swing.JTextArea;

import juego.Juego;

public class GodModeHandler extends TrickHandler {
	
	public final static String miComando = "iddqd";
	
	public GodModeHandler(TrickHandler sucesor) {
		super(sucesor);
	}
	
	@Override
	public boolean puedoSoportarlo(String comando) {
		return miComando.equals(comando);
	}
	@Override
	protected void ejecutar(Juego juego,JTextArea chat) {
		
		if(juego.getPersonaje().isDios())
			juego.getPersonaje().setDios(false);
		else
			juego.getPersonaje().setDios(true);
		System.out.println(juego.getPersonaje().isDios()); 
		
	}
}
