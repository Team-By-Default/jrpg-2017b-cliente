package chat;
import javax.swing.JTextArea;

import juego.Juego;
import mundo.Grafo;

public class NoClipHandler extends TrickHandler{
	public final static String miComando = "noclip";

	public NoClipHandler(TrickHandler sucesor) {
		super(sucesor);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean puedoSoportarlo(String comando) {
		return miComando.equals(comando);
	}

	@Override
	protected void ejecutar(Juego juego,JTextArea chat) {
		Grafo g = entidades.Entidad.getMundo().getGrafoDeTilesNoSolidos();
		entidades.Entidad.getMundo().setGrafoDeTilesNoSolidos(entidades.Entidad.getMundo().getGrafoCompleto());
		entidades.Entidad.getMundo().setGrafoCompleto(g);
		juego.getPersonaje().setClipper(!juego.getPersonaje().isClipper());
		if(juego.getPersonaje().isClipper())
			chat.append("Truco activado. Ahora el personaje puede atravesar paredes.\n");
		else
			chat.append("Truco desactivado. Ahora el personaje no puede atravesar paredes.\n");
	}

}
