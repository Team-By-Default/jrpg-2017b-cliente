package comandos;

import javax.swing.JOptionPane;

import frames.MenuCreacionPj;
import mensajeria.Paquete;

public class Registro extends ComandosCliente {

	@Override
	public void ejecutar() {
		synchronized (this) {
			
		
			Paquete paquete = (Paquete) gson.fromJson(cadenaLeida, Paquete.class);
			if (paquete.getMensaje().equals(Paquete.msjExito)) {

				// Abro el menu para la creación del personaje
				MenuCreacionPj menuCreacionPJ = new MenuCreacionPj(cliente, cliente.getPaquetePersonaje(),gson);
				menuCreacionPJ.setVisible(true);

			} else {
				if (paquete.getMensaje().equals(Paquete.msjFracaso)) {
					JOptionPane.showMessageDialog(null, "No se pudo registrar.");
				}
				// El usuario no pudo iniciar sesión
				cliente.getPaqueteUsuario().setInicioSesion(false);
			}

		}
	}

}
