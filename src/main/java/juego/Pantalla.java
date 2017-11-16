package juego;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import com.google.gson.Gson;

import chat.VentanaContactos;
import cliente.Cliente;
import estados.Estado;
import frames.MenuAsignarSkills;
import frames.MenuEscape;
import frames.MenuInventario;
import frames.MenuJugar;
import frames.MenuStats;
import mensajeria.Comando;
import mensajeria.Paquete;

public class Pantalla {

	private JFrame pantalla;
	private Canvas canvas;

	// Menus
	/**
	 * Constantes con los codigos de teclas que abren
	 * cada menu, y que sirven de key en el HashMap menus
	 */
	public static final int menuInventario = KeyEvent.VK_I;
	public static final int menuAsignar = KeyEvent.VK_A;
	public static final int menuStats = KeyEvent.VK_S;
	public static final int menuEscp = KeyEvent.VK_ESCAPE;
	public static final int ventContac = KeyEvent.VK_C;
	
	/*
	public static MenuInventario menuInventario;
	public static MenuAsignarSkills menuAsignar;
	public static MenuStats menuStats;
	public static MenuEscape menuEscp;
	public static VentanaContactos ventContac;
	*/
	
	/**
	 * Mapa de menus con el codigo de tecla que los abre
	 */
	private static HashMap<Integer, JFrame> menus = new HashMap<Integer, JFrame>();
	
		
	private final Gson gson = new Gson();
	
	public Pantalla(final String NOMBRE, final int ANCHO, final int ALTO, final Cliente cliente) {
		pantalla = new JFrame(NOMBRE);
		pantalla.setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/java/frames/IconoWome.png"));
		pantalla.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
			new ImageIcon(MenuJugar.class.getResource("/cursor.png")).getImage(),
			new Point(0,0),"custom cursor"));

		pantalla.setSize(ANCHO, ALTO);
		pantalla.setResizable(false);
		pantalla.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		
		//Inicializo el mapa de menus
		//this.menus = new HashMap<Integer, JFrame>();
		
		/*menus.put(KeyEvent.VK_C, new VentanaContactos(cliente.getJuego()));
		menus.put(KeyEvent.VK_I, new MenuInventario(cliente));
		menus.put(KeyEvent.VK_A, new MenuAsignarSkills(cliente));
		menus.put(KeyEvent.VK_S, new MenuStats(cliente));
		menus.put(KeyEvent.VK_ESCAPE, new MenuEscape(cliente));
		*/
		
		/*
		menus.put(KeyEvent.VK_C, ventContac);
		menus.put(KeyEvent.VK_I, menuInventario);
		menus.put(KeyEvent.VK_A, menuAsignar);
		menus.put(KeyEvent.VK_S, menuStats);
		menus.put(KeyEvent.VK_ESCAPE, menuEscp);
		*/
		
		menus.put(ventContac, null);
		menus.put(menuInventario, null);
		menus.put(menuAsignar, null);
		menus.put(menuStats, null);
		menus.put(menuEscp, null);
		
		pantalla.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent evt) {
				try {
					Paquete p = new Paquete();
					p.setComando(Comando.DESCONECTAR);
					p.setIp(cliente.getMiIp());
					cliente.getSalida().writeObject(gson.toJson(p));
					cliente.getEntrada().close();
					cliente.getSalida().close();
					cliente.getSocket().close();
					System.exit(0);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "Fallo al intentar cerrar la aplicación.");
					System.exit(1);
				}
			}
		});
		
		pantalla.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				//La ventana de contactos tiene que mostrarse siempre, en cualquier estado
				if (e.getKeyCode() == ventContac) {
					abrirMenu(ventContac, cliente);
					return;
				}
				
				//Las otras ventanas se muestran solo en estado juego
				if(Estado.getEstado().esEstadoDeJuego()) {
					abrirMenu(e.getKeyCode(), cliente);
				}
				
				/*Version 2 anterior
				//Si toca C, abro la ventana de contactos, no importa en qué estado esté
				if (e.getKeyCode() == KeyEvent.VK_C) {
					if (ventContac == null) {
						ventContac = new VentanaContactos(cliente.getJuego());
					}
					ventContac.setVisible(true);
				}
				
				if(Estado.getEstado().esEstadoDeJuego()) { // hacer con mapas, vale lpqtp
					switch(e.getKeyCode()) {
					//Si se toca I, abre el inventario
					case KeyEvent.VK_I:
						if (menuInventario == null) {
							menuInventario = new MenuInventario(cliente);
						}
						menuInventario.setVisible(true);
						break;
					//Si se toca A, abre el menuAsignar
					case KeyEvent.VK_A:
						if (menuAsignar == null) {
							menuAsignar = new MenuAsignarSkills(cliente);
						}
						menuAsignar.setVisible(true);
						break;
					//Si se toca S, abre el menu de stats
					case KeyEvent.VK_S:
						if (menuStats == null) {
							menuStats = new MenuStats(cliente);
						}
						menuStats.setVisible(true);
						break;
					//Si se toca ESCAPE, abre el menu escape
					case KeyEvent.VK_ESCAPE:
						if (menuEscp == null) {
							menuEscp = new MenuEscape(cliente);
						}
						menuEscp.setVisible(true);
						break;
					}
				}*/
				
				/* Version anterior
				if (e.getKeyCode() == KeyEvent.VK_I) {
					if(Estado.getEstado().esEstadoDeJuego()) {
						if (menuInventario == null) {
							menuInventario = new MenuInventario(cliente);
						}
						menuInventario.setVisible(true);
					}
				} else if (e.getKeyCode() == KeyEvent.VK_A) {
					if(Estado.getEstado().esEstadoDeJuego()) {
						if (menuAsignar == null) {
							menuAsignar = new MenuAsignarSkills(cliente);
							menuAsignar.setVisible(true);
						}
					} 
				} else if (e.getKeyCode() == KeyEvent.VK_S) {
					if(Estado.getEstado().esEstadoDeJuego()) {
						if (menuStats == null) {
							menuStats = new MenuStats(cliente);
							menuStats.setVisible(true);
						}
					}
				} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					if(Estado.getEstado().esEstadoDeJuego()) {
						if (menuEscp == null) {
							menuEscp = new MenuEscape(cliente);
							menuEscp.setVisible(true);
						}
					}
				} else if (e.getKeyCode() == KeyEvent.VK_C) {
//					if(Estado.getEstado().esEstadoDeJuego()) {
						if (ventContac == null) {
							ventContac = new VentanaContactos(cliente.getJuego());
							ventContac.setVisible(true);
						}
//					}
				}
				*/
			}
		});


		pantalla.setLocationRelativeTo(null);
		pantalla.setVisible(false);

		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(ANCHO, ALTO));
		canvas.setMaximumSize(new Dimension(ANCHO, ALTO));
		canvas.setMinimumSize(new Dimension(ANCHO, ALTO));
		canvas.setFocusable(false);

		pantalla.add(canvas);
		pantalla.pack();
	}

	public Canvas getCanvas() {
		return canvas;
	}

	public JFrame getFrame() {
		return pantalla;
	}

	public void mostrar() {
		pantalla.setVisible(true);
	}

	public static void centerString(Graphics g, Rectangle r, String s) {
	    FontRenderContext frc = new FontRenderContext(null, true, true);

	    Rectangle2D r2D = g.getFont().getStringBounds(s, frc);
	    int rWidth = (int) Math.round(r2D.getWidth());
	    int rHeight = (int) Math.round(r2D.getHeight());
	    int rX = (int) Math.round(r2D.getX());
	    int rY = (int) Math.round(r2D.getY());

	    int a = (r.width / 2) - (rWidth / 2) - rX;
	    int b = (r.height / 2) - (rHeight / 2) - rY;

	    g.drawString(s, r.x + a, r.y + b);
	}
	
	/**
	 * Abre la ventana del menu que recibe por parametro
	 * @param menu: codigo del menu a mostrar
	 * @param cliente: Cliente del que se van a mostrar los datos
	 */
	public static void abrirMenu(final int menu, final Cliente cliente) {
		if(menus.get(menu) == null) {
			//Si la ventana no existia, la creo
			//No logro deshacerme de este switch...
			switch(menu) {
			case ventContac:
				menus.put(ventContac, new VentanaContactos(cliente.getJuego()));
				break;
			case menuInventario:
				menus.put(menuInventario, new MenuInventario(cliente));
				break;
			case menuAsignar:
				menus.put(menuAsignar, new MenuAsignarSkills(cliente));
				break;
			case menuStats:
				menus.put(menuStats, new MenuStats(cliente));
				break;
			case menuEscp:
				menus.put(menuEscp, new MenuEscape(cliente));
			}
		}
		menus.get(menu).setVisible(true);
	}
	
	/**
	 * Cierra la ventana del menu que recibe por parametro
	 * @param menu: codigo del menu a cerrar
	 */
	public static void cerrarMenu(final int menu) {
		menus.put(menu, null);
	}
	
	/**
	 * Devuelve la ventana de contactos para que pueda ser modificada
	 * por Talk y MiChat.
	 * @return Ventana de contactos
	 */
	public static VentanaContactos getVentContac() {
		return (VentanaContactos) menus.get(ventContac);
	}
}