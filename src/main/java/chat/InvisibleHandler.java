package chat;

public class InvisibleHandler extends TrickHandler {
	public final static String miComando = "invisible";

	public InvisibleHandler(TrickHandler sucesor) {
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
