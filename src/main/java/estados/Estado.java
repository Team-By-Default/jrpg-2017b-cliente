package estados;

import java.awt.Graphics;

import juego.Juego;

public abstract class Estado {

	private static Estado estadoActual = null;

	// Tipo de estados
	public static int estadoOffline = 0;
	public static int estadoJuego = 1;
	public static int estadoBatalla = 2;
	public static int estadoBatallaNPC = 3;

	protected Juego juego;

	public Estado(Juego juego) {
		this.juego = juego;
	}

	public abstract void actualizar();

	public abstract void graficar(Graphics g);

	public static void setEstado(Estado estado) {
		estadoActual = estado;
	}

	public static Estado getEstado() {
		return estadoActual;
	}
	
	/**
	 * Devuelve si es estado de juego
	 * @return
	 */
	public abstract boolean esEstadoDeJuego();
	
	/**
	 * Devuelve si es estado de batalla contra otro personaje
	 * @return
	 */
	public abstract boolean esEstadoBatalla();
	
	/**
	 * Devuelve si es estado de batalla contra un NPC
	 * @return
	 */
	public abstract boolean esEstadoBatallaNPC();
}
