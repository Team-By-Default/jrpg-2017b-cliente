package chat;

public class TinyDaddyHandler extends TrickHandler{
	public final static String miComando = "tinydaddy";
	
	public TinyDaddyHandler(TrickHandler sucesor) {
		super(sucesor);
		// TODO Auto-generated constructor stub
	}

	public void ejecutar() {
		System.out.println("Tiny daddy");
	}
	@Override
	public boolean puedoSoportarlo(String comando) {
		return this.miComando.equals(comando);
	}
}
