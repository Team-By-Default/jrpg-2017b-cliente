package mensajeria;

import java.io.Serializable;

import javax.swing.JOptionPane;

public class Paquete implements Serializable, Cloneable {

	public static String msjExito = "1";
	public static String msjFracaso = "0";

	private String mensaje;
	private String ip;
	private int comando;
	public Paquete() {

	}

	public Paquete(String mensaje, String nick, String ip, int comando) {
		this.mensaje = mensaje;
		this.ip = ip;
		this.comando = comando;
	}

	public Paquete(String mensaje, int comando) {
		this.mensaje = mensaje;
		this.comando = comando;
	}

	public Paquete(int comando) {
		this.comando = comando;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setComando(int comando) {
		this.comando = comando;
	}

	public String getMensaje() {
		return mensaje;
	}


	public String getIp() {
		return ip;
	}

	public int getComando() {
		return comando;
	}

	@Override
	public Object clone() {
		Object obj = null;
		try {
			obj = super.clone();
		} catch (CloneNotSupportedException ex) {
			JOptionPane.showMessageDialog(null, "Error al clonar");

		}
		return obj;
	}
	
	/**
	 * Prepara el comando según el nombre del paquete de comandos y el número
	 * de comando previamente seteado
	 * @param nombrePaquete es el nombre del paquete que tiene los comandos
	 * @return el objeto Comando
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	public Comando getObjeto(String nombrePaquete) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		//Prefiero tirar excepciones y que lo resuelva el llamador a devolver null y que el llamador no se lo espere
		return (Comando) Class.forName(nombrePaquete + "." + Comando.CLASSNAMES[comando]).newInstance();
		
		/* Version anterior
		try {
			Comando c;
			c = (Comando) Class.forName(nombrePaquete + "." + Comando.CLASSNAMES[comando]).newInstance();
			return c;
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			return null;
		}
		*/

	}
	
	/**
	 * Prepara el comando según el nombre del paquete de comandos y el número
	 * de comando indicado
	 * @param nombrePaquete es el nombre del paquete que tiene los comandos
	 * @param accion es el número de comando
	 * @return el objeto Comando
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	public static Comando getObjetoSet(String nombrePaquete, int accion) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		//Prefiero tirar excepciones y que lo resuelva el llamador a devolver null y que el llamador no se lo espere
		return (Comando) Class.forName(nombrePaquete + "." + Comando.CLASSNAMESBIS[accion]).newInstance();
		
		/*Version anterior
		try {
			Comando c;
			c = (Comando) Class.forName(nombrePaquete + "." + Comando.CLASSNAMESBIS[accion]).newInstance();
			return c;
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			return null;
		}
		*/
	}


}
