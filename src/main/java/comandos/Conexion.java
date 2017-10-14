package comandos;

import java.util.Map;

import javax.swing.DefaultListModel;

import cliente.Cliente;
import chat.VentanaContactos;
import mensajeria.PaqueteDePersonajes;
import mensajeria.PaquetePersonaje;

public class Conexion extends ComandosEscucha {

	
	@Override
	public void ejecutar() {
		PaqueteDePersonajes pdp = (PaqueteDePersonajes) gson.fromJson(cadenaLeida, PaqueteDePersonajes.class);
		juego.setPersonajesConectados(pdp.getPersonajes());
		actualizarLista(pdp);
	}

	private void actualizarLista(final PaqueteDePersonajes pdp) {
		DefaultListModel<String> modelo = new DefaultListModel<String>();
		VentanaContactos.getList().removeAll();
		for (Map.Entry<Integer, PaquetePersonaje> personaje : pdp.getPersonajes().entrySet()) {
			modelo.addElement(personaje.getValue().getNombre());
		}
		modelo.removeElement(juego.getPersonaje().getNombre());
		VentanaContactos.getList().setModel(modelo);
	}
}
