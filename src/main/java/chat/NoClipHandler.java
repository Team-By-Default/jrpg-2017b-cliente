package chat;
import juego.Juego;
import mundo.Grafo;
import entidades.Entidad;

public class NoClipHandler extends TrickHandler{
	public final static String miComando = "noclip";

	public NoClipHandler(TrickHandler sucesor) {
		super(sucesor);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean puedoSoportarlo(String comando) {
		return this.miComando.equals(comando);
	}

	@Override
	protected void ejecutar(Juego juego) {
		System.out.println("Atravesar paredes");
		Grafo g = entidades.Entidad.getMundo().getGrafoDeTilesNoSolidos();
		entidades.Entidad.getMundo().setGrafoDeTilesNoSolidos(entidades.Entidad.getMundo().getGrafoCompleto());
		entidades.Entidad.getMundo().setGrafoCompleto(g);
	}

}
