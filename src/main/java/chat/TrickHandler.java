package chat;

public abstract class TrickHandler {
	protected TrickHandler sucesor;
	
	public TrickHandler(TrickHandler sucesor) {
		this.sucesor = sucesor;
	}
	
	public void ejecutarComando(String comando) {
		if(puedoSoportarlo(comando))
			ejecutar();
		else
			sucesor.ejecutarComando(comando);
	}
	
	public abstract boolean puedoSoportarlo(String comando);
	public abstract void ejecutar();
}
