package chat;

import javax.swing.JTextArea;

import estados.Estado;
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
		
		juego.getCliente().getPaquetePersonaje().multiMultiply(2);
		
		//Si está en batalla...
		if(Estado.getEstado().esEstadoBatalla()) {
			//Seteo el multiplicador del personaje durante la batalla
			juego.getEstadoBatalla().getPersonaje().setMultiDaddy(juego.getCliente().getPaquetePersonaje().getMultiplicador());
			//Recalculo los puntos de ataque
			juego.getEstadoBatalla().getPersonaje().setAtaque(juego.getEstadoBatalla().getPersonaje().calcularPuntosDeAtaque());
		}
		else if(Estado.getEstado().esEstadoBatallaNPC()) {
			//Seteo el multiplicador del personaje durante la batalla
			juego.getEstadoBatallaNPC().getPersonaje().setMultiDaddy(juego.getCliente().getPaquetePersonaje().getMultiplicador());
			//Recalculo los puntos de ataque
			juego.getEstadoBatallaNPC().getPersonaje().setAtaque(juego.getEstadoBatallaNPC().getPersonaje().calcularPuntosDeAtaque());
		}		
		chat.append("Fuerza aumentada a "+ juego.getCliente().getPaquetePersonaje().getFuerzaTrucada()+ "."+ System.lineSeparator());
	}
}
