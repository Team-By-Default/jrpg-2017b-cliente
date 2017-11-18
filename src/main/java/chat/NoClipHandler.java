package chat;

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
	public void ejecutar() {
		// TODO Auto-generated method stub
		
	}

}
