package chat;

import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import com.google.gson.Gson;
import juego.Juego;
import mensajeria.Comando;

public abstract class TrickHandler {
	protected TrickHandler sucesor;
	protected final Gson gson;
	
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
	public void ejecutarComando(String comando, Juego juego,JTextArea chat) {
		if(puedoSoportarlo(comando)) {
			ejecutar(juego,chat);
			juego.getCliente().getPaquetePersonaje().setComando(Comando.CHEAT);
			try {
				juego.getCliente().getSalida().writeObject(gson.toJson(juego.getCliente().getPaquetePersonaje()));
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Error al aplicar el truco... ¡Dejá de hacer trampa!");
			}
		}
		else
			sucesor.ejecutarComando(comando, juego,chat);
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
	protected abstract void ejecutar(Juego juego,JTextArea chat);
}
