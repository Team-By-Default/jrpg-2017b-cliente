package chat;

import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import com.google.gson.Gson;
import juego.Juego;
import mensajeria.Comando;

public abstract class TrickHandler {
	protected TrickHandler sucesor;
	private final Gson gson;
	
	public TrickHandler(TrickHandler sucesor) {
		this.sucesor = sucesor;
		this.gson = new Gson();
	}
	
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
	
	protected abstract boolean puedoSoportarlo(String comando);
	protected abstract void ejecutar(Juego juego,JTextArea chat);
}
