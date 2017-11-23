package chat;

import javax.swing.JTextArea;

import juego.Juego;

public class BigDaddyHandler extends TrickHandler {
	public final static String miComando = "bigdaddy";
	
	public BigDaddyHandler(TrickHandler sucesor) {
		super(sucesor);
	}
	@Override
	public boolean puedoSoportarlo(String comando) {
		return miComando.equals(comando);
	}
	@Override
	protected void ejecutar(Juego juego,JTextArea chat) {
		
		juego.getPersonaje().setMultiplicador(2);
		System.out.println(juego.getPersonaje().getMultiplicador()); 
		System.out.println(juego.getPersonaje().getMultiplicador() * juego.getPersonaje().getFuerza());
		
	}
}
