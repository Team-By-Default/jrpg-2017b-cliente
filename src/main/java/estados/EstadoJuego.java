package estados;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JOptionPane;

import com.google.gson.Gson;

import entidades.Entidad;
import interfaz.EstadoDePersonaje;
import interfaz.MenuInfoPersonaje;
import juego.Juego;
import juego.Pantalla;
import mensajeria.Comando;
import mensajeria.PaqueteMovimiento;
import mensajeria.PaqueteNPC;
import mensajeria.PaquetePersonaje;
import mundo.Mundo;
import recursos.Recursos;

public class EstadoJuego extends Estado {

	private Entidad entidadPersonaje;
	private PaquetePersonaje paquetePersonaje;
	private Mundo mundo;
	private Map<Integer, PaqueteMovimiento> ubicacionPersonajes;
	private Map<Integer, PaquetePersonaje> personajesConectados;
	private Map<Integer, PaqueteMovimiento> ubicacionNPCs;
	private Map<Integer, PaqueteNPC> NPCs;
	private boolean haySolicitud;
	private int tipoSolicitud;

	private final Gson gson = new Gson();

	private BufferedImage miniaturaPersonaje;

	MenuInfoPersonaje menuEnemigo;

	public EstadoJuego(Juego juego) {
		super(juego);
		mundo = new Mundo(juego, "recursos/" + getMundo() + ".txt", "recursos/" + getMundo() + ".txt");
		paquetePersonaje = juego.getPersonaje();
		entidadPersonaje = new Entidad(juego, mundo, 64, 64, juego.getPersonaje().getNombre(), 0, 0, Recursos.personaje.get(juego.getPersonaje().getRaza()), 150);
		miniaturaPersonaje = Recursos.personaje.get(paquetePersonaje.getRaza()).get(5)[0];

		try {
			// Le envio al servidor que me conecte al mapa y mi posicion
			juego.getPersonaje().setComando(Comando.CONEXION);
			juego.getPersonaje().setEstado(Estado.estadoJuego);
			juego.getCliente().getSalida().writeObject(gson.toJson(juego.getPersonaje(), PaquetePersonaje.class));
			juego.getCliente().getSalida().writeObject(gson.toJson(juego.getUbicacionPersonaje(), PaqueteMovimiento.class));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Fallo la conexión con el servidor al ingresar al mundo");
		}
	}

	@Override
	public void actualizar() {
		mundo.actualizar();
		entidadPersonaje.actualizar();
	}

	/**
	 * Grafica toda la pantalla. El mundo, personajes, NPCs, etc
	 */
	@Override
	public void graficar(Graphics g) {
		//Dibujar mapa, NPCs, obstaculos y personajes, en ese orden (importante para que no aparezcan NPCs sobre un muro o cosas asi)
		g.drawImage(Recursos.background, 0, 0, juego.getAncho(), juego.getAlto(), null);
		mundo.graficar(g);
		graficarNPCs(g);
		mundo.graficarObstaculos(g);
		graficarPersonajes(g);
		//Dibujar botones, marco, etc
		entidadPersonaje.graficarNombre(g);
		g.drawImage(Recursos.marco, 0, 0, juego.getAncho(), juego.getAlto(), null);
		EstadoDePersonaje.dibujarEstadoDePersonaje(g, 5, 5, paquetePersonaje, miniaturaPersonaje);
		g.drawImage(Recursos.mochila, 738, 545, 59, 52, null);
		g.drawImage(Recursos.menu, 3, 562, 102, 35, null);
		g.drawImage(Recursos.chat, 3, 524, 102, 35, null);
		if(haySolicitud)
			menuEnemigo.graficar(g, tipoSolicitud);
	}

	/**
	 * Grafica todos los personajes sobre el mapa
	 * @param g
	 */
	public void graficarPersonajes(Graphics g) {

		if(juego.getPersonajesConectados() != null){
			personajesConectados = new HashMap<Integer, PaquetePersonaje>(juego.getPersonajesConectados());
			ubicacionPersonajes = new HashMap<Integer, PaqueteMovimiento>(juego.getUbicacionPersonajes());
			Iterator<Integer> it = personajesConectados.keySet().iterator();
			int key;
			PaqueteMovimiento actualMov;
			
			g.setColor(Color.WHITE);
			g.setFont(new Font("Book Antiqua", Font.PLAIN, 15));
			
			while (it.hasNext()) {
				key = it.next();
				actualMov = ubicacionPersonajes.get(key);
				
				//Si el actual no es mi personaje y está en estadoJuego y no es invisible o yo soy invisible y veo a todos...
				if (actualMov != null && 
						actualMov.getIdPersonaje() != juego.getPersonaje().getId() && 
						personajesConectados.get(actualMov.getIdPersonaje()).getEstado() == Estado.estadoJuego &&
						(juego.getPersonaje().isInvisible() || !personajesConectados.get(actualMov.getIdPersonaje()).isInvisible())) {
					
					//... lo dibujo
					Pantalla.centerString(g, 
							new Rectangle((int) (actualMov.getPosX() - juego.getCamara().getxOffset() + 32), 
									(int) (actualMov.getPosY() - juego.getCamara().getyOffset() - 20 ),
									0, 10), 
							personajesConectados.get(actualMov.getIdPersonaje()).getNombre());
					
					g.drawImage(Recursos.personaje.get(
							personajesConectados.get(actualMov.getIdPersonaje()).getRaza()
							).get(actualMov.getDireccion())[actualMov.getFrame()],
							(int) (actualMov.getPosX() - juego.getCamara().getxOffset() ), 
							(int) (actualMov.getPosY() - juego.getCamara().getyOffset()), 
							64, 64, null);
				}
			}
		}
	}
	
	/**
	 * Grafica todos los NPC sobre el mapa
	 * @param g
	 */
	public void graficarNPCs(Graphics g) {
		
		if(juego.getNPCs() != null){
			NPCs = new HashMap<Integer, PaqueteNPC>(juego.getNPCs());
			ubicacionNPCs = new HashMap<Integer, PaqueteMovimiento>(juego.getUbicacionNPCs());
			
			Iterator<Integer> it = NPCs.keySet().iterator();
			int key;
			PaqueteMovimiento actual;
			g.setColor(Color.WHITE);
			g.setFont(new Font("Book Antiqua", Font.PLAIN, 15));
			
			while (it.hasNext()) {
				key = it.next();
				actual = ubicacionNPCs.get(key);
				
				//Si no está peleando, lo grafico
				if (actual != null && !NPCs.get(key).estaPeleando()) {
						Pantalla.centerString(g, new Rectangle((int) (actual.getPosX() - juego.getCamara().getxOffset() + 32), (int) (actual.getPosY() - juego.getCamara().getyOffset() - 20 ), 0, 10), NPCs.get(actual.getIdPersonaje()).getNombre());
						g.drawImage( Recursos.monstruo, (int) (actual.getPosX() - juego.getCamara().getxOffset() ), (int) (actual.getPosY() - juego.getCamara().getyOffset()), 64, 64, null);
				}
			}
		}
	}	

	public Entidad getPersonaje() {
		return entidadPersonaje;
	}
	
	/**
	 * Devuelve el nombre del mundo en que está
	 * @return nombre del mundo
	 */
	private String getMundo() {
		int mundo = juego.getPersonaje().getMapa();
		String[] nombres = {"Aubenor", "Aris", "Eodrim"};
		if(mundo > 0 && mundo <= nombres.length)
			return nombres[mundo - 1];
		return null;
	}

	public void setHaySolicitud(boolean b, PaquetePersonaje enemigo, int tipoSolicitud) {
		haySolicitud = b;
		
		// menu que mostrara al enemigo
		menuEnemigo = new MenuInfoPersonaje(300, 50, enemigo);
		this.tipoSolicitud = tipoSolicitud;
	}

	public boolean getHaySolicitud() {
		return haySolicitud;
	}

	public void actualizarPersonaje() {
		paquetePersonaje = juego.getPersonaje();
	}

	public MenuInfoPersonaje getMenuEnemigo(){
		return menuEnemigo;
	}

	public int getTipoSolicitud() {
		return tipoSolicitud;
	}
	
	@Override
	public boolean esEstadoDeJuego() {
		return true;
	}

	@Override
	public boolean esEstadoBatalla() {
		return false;
	}

	@Override
	public boolean esEstadoBatallaNPC() {
		return false;
	}
}