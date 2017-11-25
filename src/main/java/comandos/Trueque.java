package comandos;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import dominio.Casta;
import dominio.Item;
import dominio.Personaje;
import mensajeria.Comando;
import mensajeria.PaqueteComerciar;
import mensajeria.PaquetePersonaje;

public class Trueque extends ComandosEscucha {

	@Override
	public void ejecutar() {
		PaqueteComerciar paqueteComerciar;
		PaquetePersonaje paquetePersonaje;
		paqueteComerciar = gson.fromJson(cadenaLeida, PaqueteComerciar.class);
		Personaje pj = null;
		
		juego.getCliente().getPaquetePersonaje().removerBonus();
		
		String nombre = juego.getCliente().getPaquetePersonaje().getNombre();	
		int salud = juego.getCliente().getPaquetePersonaje().getSaludTope();
		int energia = juego.getCliente().getPaquetePersonaje().getEnergiaTope();
		int fuerza = juego.getCliente().getPaquetePersonaje().getFuerza();
		int destreza = juego.getCliente().getPaquetePersonaje().getDestreza();
		int inteligencia = juego.getCliente().getPaquetePersonaje().getInteligencia();
		int experiencia = juego.getCliente().getPaquetePersonaje().getExperiencia();
		int nivel = juego.getCliente().getPaquetePersonaje().getNivel();
		int id = juego.getCliente().getPaquetePersonaje().getId();

		Casta casta = null;

		try {
			casta = (Casta) Class.forName("dominio" + "." + juego.getCliente().getPaquetePersonaje().getCasta()).newInstance();
			pj = (Personaje) Class.forName("dominio" + "." + juego.getCliente().getPaquetePersonaje().getRaza()).getConstructor(String.class, Integer.TYPE, 
					Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Casta.class, Integer.TYPE, Integer.TYPE, Integer.TYPE).
					newInstance(nombre, salud, energia, fuerza, destreza, inteligencia, casta,
							experiencia, nivel, id);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			JOptionPane.showMessageDialog(null, "Error al crear la batalla");
		}
		
		// Si soy yo mismo, tengo que cambiar los items a darme, y despues trueque
		if(id == paqueteComerciar.getId()){
			paqueteComerciar.getItemsADar().removeAll(paqueteComerciar.getItemsADar());
			ArrayList<Item> items = juego.getPersonajesConectados().get(paqueteComerciar.getIdEnemigo()).getItems();
			boolean loop = true;
			int i = 0;
			while (juego.getCliente().getM1().getObtener().size() > 0) {
				while(loop) {
					if (items.get(i).getNombre().equals(juego.getCliente().getM1().getObtener().get(0))) {
						paqueteComerciar.getItemsADar().add(items.get(i));
						juego.getCliente().getM1().getObtener().remove(0);
						loop = false;
					}
					i++;
				}
				loop = true;
				i = 0;
			}
			pj.trueque(juego.getCliente().getPaquetePersonaje().getItems(),paqueteComerciar.getItemsADar(),juego.getCliente().getM1().getDar());
		} else {
			
			// sino soy yo esta todo ok y trueque
			pj.trueque(juego.getCliente().getPaquetePersonaje().getItems(),paqueteComerciar.getItemsADar(),juego.getCliente().getM1().getDar());						
		}
		juego.getCliente().getPaquetePersonaje().actualizarTrueque(pj.getItems());
		paquetePersonaje = juego.getCliente().getPaquetePersonaje();
		paquetePersonaje.setComando(Comando.ACTUALIZARTRUEQUE);
		juego.getCliente().setM1(null);
		try {
			juego.getCliente().getSalida().writeObject(gson.toJson(paquetePersonaje));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error al actualizar trueque");

		}

	}

}
