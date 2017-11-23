package chat;

import javax.swing.JTextArea;

import juego.Juego;

public class TinyDaddyHandler extends TrickHandler{
	public final static String miComando = "tinydaddy";
	
	public TinyDaddyHandler(TrickHandler sucesor) {
		super(sucesor);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean puedoSoportarlo(String comando) {
		return miComando.equals(comando);
	}

	@Override
	protected void ejecutar(Juego juego,JTextArea chat) {
		
		juego.getPersonaje().setMultiplicador(0.5);
		System.out.println(juego.getPersonaje().getMultiplicador()); 
		System.out.println(juego.getPersonaje().getMultiplicador() * juego.getPersonaje().getFuerza()); 
		
	}
}
