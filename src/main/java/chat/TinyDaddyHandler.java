package chat;

import estados.Estado;
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
		
		juego.getPersonaje().setMultiplicador(0.5);
		
		if(juego.getPersonaje().getEstado() == Estado.estadoBatalla) {
			juego.getEstadoBatalla().getPersonaje().setFuerza((int)
					juego.getPersonaje().getFuerzaTrucada());
			System.out.println("Personaje en batalla tiene fuerza " + juego.getPersonaje().getFuerzaTrucada());
		}
		
		System.out.println("Paquete personaje tiene multi " + juego.getPersonaje().getMultiplicador()); 
		System.out.println("Paquete personaje tiene fuerza " + juego.getPersonaje().getFuerzaTrucada());
		
	}
}
