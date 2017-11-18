package chat;

public class BigDaddyHandler extends TrickHandler {
	public final static String miComando = "bigdaddy";
	
	public BigDaddyHandler(TrickHandler sucesor) {
		super(sucesor);
	}
	public void ejecutar() {
		System.out.println("Big daddy");
	}
	@Override
	public boolean puedoSoportarlo(String comando) {
		return this.miComando.equals(comando);
	}
}
