package chat;

import estados.Estado;
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
		
		juego.getPersonaje().setMultiplicador(2);
		
		if(juego.getPersonaje().getEstado() == Estado.estadoBatalla) {
			juego.getEstadoBatalla().getPersonaje().setFuerza((int)
					juego.getPersonaje().getFuerzaTrucada());
			System.out.println("Personaje en batalla tiene fuerza " + juego.getPersonaje().getFuerzaTrucada());
		}
		
		System.out.println("Paquete personaje tiene multi " + juego.getPersonaje().getMultiplicador()); 
		System.out.println("Paquete personaje tiene fuerza " + juego.getPersonaje().getFuerzaTrucada());
		
		
	}
}
