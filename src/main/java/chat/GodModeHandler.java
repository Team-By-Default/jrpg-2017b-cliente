package chat;

public class GodModeHandler extends TrickHandler {
	
	public final static String miComando = "iddqd";
	
	public GodModeHandler(TrickHandler sucesor) {
		super(sucesor);
	}
	public void ejecutar() {
		System.out.println("Modo dios");
	}
	@Override
	public boolean puedoSoportarlo(String comando) {
		return this.miComando.equals(comando);
	}
}
