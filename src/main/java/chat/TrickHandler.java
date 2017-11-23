package chat;

import java.io.IOException;
import javax.swing.JOptionPane;
import com.google.gson.Gson;
import juego.Juego;
import mensajeria.Comando;

public abstract class TrickHandler {
	protected TrickHandler sucesor;
	private final Gson gson;
	
	/**
	 * Crea un nuevo trick handler seteando su sucesor de la ChainOfResponsabilty
	 * @param sucesor
	 */
	public TrickHandler(TrickHandler sucesor) {
		this.sucesor = sucesor;
		this.gson = new Gson();
	}
	
	/**
	 * Si puede, ejecuta el comando, sino se lo delega al siguiente de la
	 * ChainOfResponsability, pero alguien lo va a ejecutar seguro.
	 * @param comando: nombre del comando
	 * @param juego: para poder afectar a los recursos del juego
	 */
	public void ejecutarComando(String comando, Juego juego) {
		if(puedoSoportarlo(comando)) {
			ejecutar(juego);
			juego.getPersonaje().setComando(Comando.CHEAT);
			try {
				juego.getCliente().getSalida().writeObject(gson.toJson(juego.getPersonaje()));
				System.out.println("Estoy enviando el personaje " + juego.getPersonaje().getId() + " con multi " + juego.getPersonaje().getMultiplicador());
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Error al aplicar el truco... ¡Dejá de hacer trampa!");
			}
		}
		else
			sucesor.ejecutarComando(comando, juego);
	}
	
	/**
	 * Define si es su propio comando
	 * @param comando: nombre del comando
	 * @return
	 */
	protected abstract boolean puedoSoportarlo(String comando);
	/**
	 * Tiene la logica del comando que soporta
	 * @param juego: para afectar a los recursos del juego
	 */
	protected abstract void ejecutar(Juego juego);
}
