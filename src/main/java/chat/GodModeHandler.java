package chat;

import javax.swing.JTextArea;

import estados.Estado;
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
	protected void ejecutar(Juego juego,JTextArea chat) {
		
		juego.getCliente().getPaquetePersonaje().setDios(
				!juego.getCliente().getPaquetePersonaje().isDios());
		
		//Si está en batalla...
		if(Estado.getEstado().esEstadoBatalla()) {
			//Seteo el estado Dios del personaje durante la batalla
			juego.getEstadoBatalla().getPersonaje().setGod(juego.getCliente().getPaquetePersonaje().isDios());//ver si funciona
			System.out.println("You know is " + juego.getEstadoBatalla().getPersonaje().isDios());
		}
		else if(Estado.getEstado().esEstadoBatallaNPC()) {
			//Seteo el estado Dios del personaje durante la batalla
			juego.getEstadoBatallaNPC().getPersonaje().setGod(juego.getCliente().getPaquetePersonaje().isDios());//ver si funciona
		}
		
		chat.append("I am a " + juego.getCliente().getPaquetePersonaje().isDios() + " GOD."+ System.lineSeparator());
		System.out.println("I am a " + juego.getCliente().getPaquetePersonaje().isDios() + " GOD"); 
		
	}
}
