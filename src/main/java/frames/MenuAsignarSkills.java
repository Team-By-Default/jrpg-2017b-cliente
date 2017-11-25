package frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;

import cliente.Cliente;
import dominio.Item;
import juego.Pantalla;
import mensajeria.Comando;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MenuAsignarSkills extends JFrame {

	private JPanel contentPane;
	
	/* Todo lo Inicial son los puntajes que tengo
	 * al abrir la ventana
	 */
	private int puntosAsignarInicial = 10;
	private int puntosFuerzaInicial = 0;
	private int puntosDestrezaInicial = 0;
	private int puntosInteligenciaInicial = 0;
	
	/* Todo lo Base son los puntajes que tendría si
	 * nunca hubiera asignado ningún punto de Skill.
	 * Es a lo que debe resetear cuando toco asignar.
	 */
	private int puntosFuerzaBase=10;
	private int puntosDestrezaBase=10;
	private int puntosInteligenciaBase=10;
	private int puntosAsignarBase=0;
	
	/* Y estas son las variables que voy a ir modificando
	 * mientras uso la pantalla y antes de confirmar.
	 */
	private int puntosAsignar = puntosAsignarInicial;
	private int puntosFuerza = puntosFuerzaInicial;
	private int puntosDestreza = puntosDestrezaInicial;
	private int puntosInteligencia = puntosInteligenciaInicial;
	
	private final Gson gson = new Gson();

	/**
	 * Genera el menu Asignar Skills
	 */
	public MenuAsignarSkills(final Cliente cliente) {
		//Calculo cuanto tengo de cada habilidad actualmente. Esto incluye bonus por casta e items
		puntosFuerzaInicial = cliente.getPaquetePersonaje().getFuerza();
		puntosDestrezaInicial = cliente.getPaquetePersonaje().getDestreza();
		puntosInteligenciaInicial = cliente.getPaquetePersonaje().getInteligencia();
		
		/* La base de una habilidad es lo que tendría sin sumarle ningún punto de skill,
		 * a lo que debería resetear cuando toca Reiniciar.
		 * Es la suma de los bonus de casta e items.
		 */
		if(cliente.getPaquetePersonaje().getCasta().equals("Asesino"))
			puntosDestrezaBase+=5;
		if(cliente.getPaquetePersonaje().getCasta().equals("Guerrero"))
			puntosFuerzaBase+=5;
		if(cliente.getPaquetePersonaje().getCasta().equals("Hechicero"))
			puntosInteligenciaBase+=5;
		for(Item item : cliente.getPaquetePersonaje().getItems()){
			puntosDestrezaBase += item.getBonusDestreza();
			puntosFuerzaBase += item.getBonusFuerza();
			puntosInteligenciaBase += item.getBonusInteligencia();
		}
		
		//La base de los puntos a asignar son los ganados por nivel
		puntosAsignarBase = (cliente.getPaquetePersonaje().getNivel()-1)*3;
		
		//Los puntos a asignar inicial son los todavía no asignados a las habilidades
		puntosAsignarInicial = puntosAsignarBase - 
				(puntosFuerzaInicial - puntosFuerzaBase) - 
				(puntosDestrezaInicial - puntosDestrezaBase) - 
				(puntosInteligenciaInicial - puntosInteligenciaBase);
		
		puntosAsignar = puntosAsignarInicial;
		puntosFuerza = puntosFuerzaInicial;
		puntosDestreza = puntosDestrezaInicial;
		puntosInteligencia = puntosInteligenciaInicial;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("recursos//1up.png"));
		setTitle("Asignar");
		setBounds(100, 100, 298, 294);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
		setLocationRelativeTo(null);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				Pantalla.cerrarMenu(Pantalla.menuAsignar);
				dispose();
			}
		});
		
		final JLabel labelFuerza = new JLabel("");
		labelFuerza.setForeground(Color.WHITE);
		labelFuerza.setHorizontalAlignment(SwingConstants.CENTER);
		labelFuerza.setBounds(50, 101, 56, 16);
		labelFuerza.setText(String.valueOf(puntosFuerzaInicial));
		contentPane.add(labelFuerza);
		
		final JLabel labelDestreza = new JLabel("");
		labelDestreza.setForeground(Color.WHITE);
		labelDestreza.setHorizontalAlignment(SwingConstants.CENTER);
		labelDestreza.setBounds(50, 159, 56, 16);
		labelDestreza.setText(String.valueOf(puntosDestrezaInicial));
		contentPane.add(labelDestreza);
		
		final JLabel labelInteligencia = new JLabel("");
		labelInteligencia.setForeground(Color.WHITE);
		labelInteligencia.setHorizontalAlignment(SwingConstants.CENTER);
		labelInteligencia.setBounds(50, 217, 56, 16);
		labelInteligencia.setText(String.valueOf(puntosInteligenciaInicial));
		contentPane.add(labelInteligencia);
		
		final JLabel labelPuntos = new JLabel("");
		labelPuntos.setForeground(Color.WHITE);
		labelPuntos.setHorizontalAlignment(SwingConstants.CENTER);
		labelPuntos.setBounds(39, 41, 83, 26);
		labelPuntos.setText(String.valueOf(puntosAsignarInicial));
		contentPane.add(labelPuntos);
		
		final JLabel lblCantidadDePuntos = new JLabel("Cantidad de Puntos a Asignar");
		lblCantidadDePuntos.setForeground(Color.WHITE);
		lblCantidadDePuntos.setBounds(12, 13, 177, 29);
		contentPane.add(lblCantidadDePuntos);
		
		final JLabel lblInteligencia = new JLabel("Inteligencia");
		lblInteligencia.setForeground(Color.WHITE);
		lblInteligencia.setHorizontalAlignment(SwingConstants.CENTER);
		lblInteligencia.setBounds(39, 188, 83, 16);
		contentPane.add(lblInteligencia);
		
		JLabel lblDestreza = new JLabel("Destreza");
		lblDestreza.setForeground(Color.WHITE);
		lblDestreza.setHorizontalAlignment(SwingConstants.CENTER);
		lblDestreza.setBounds(50, 130, 56, 16);
		contentPane.add(lblDestreza);
		
		final JLabel lblFuerza = new JLabel("Fuerza");
		lblFuerza.setForeground(Color.WHITE);
		lblFuerza.setHorizontalAlignment(SwingConstants.CENTER);
		lblFuerza.setBounds(50, 72, 56, 16);
		contentPane.add(lblFuerza);
		
		final JButton buttonConfirm = new JButton("Confirmar");
		ImageIcon icono_confirm = new ImageIcon("recursos//botonConfirmar.png");
		buttonConfirm.setIcon(icono_confirm);
		buttonConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {;
				puntosAsignarInicial = puntosAsignar;
				int bonusF = puntosFuerza-cliente.getPaquetePersonaje().getFuerza();
				int bonusD = puntosDestreza-cliente.getPaquetePersonaje().getDestreza();
				int bonusI = puntosInteligencia-cliente.getPaquetePersonaje().getInteligencia();
				cliente.getPaquetePersonaje().useBonus(0, 0, bonusF, bonusD, bonusI);
				cliente.getPaquetePersonaje().removerBonus();
				cliente.getPaquetePersonaje().setComando(Comando.ACTUALIZARPERSONAJELV);
				try {
					cliente.getSalida().writeObject(gson.toJson(cliente.getPaquetePersonaje()));
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Error al actualizar stats");

				}
				JOptionPane.showMessageDialog(null,"Se han actualizado tus atributos.");
				Pantalla.cerrarMenu(Pantalla.menuAsignar);
				dispose();
			}
		});
		buttonConfirm.setBounds(176, 112, 97, 25);
		contentPane.add(buttonConfirm);
		
		final JButton buttonMinus = new JButton("");
		final JButton buttonMinus1 = new JButton("");
		final JButton buttonMinus2 = new JButton("");
		final JButton buttonMore = new JButton("");
		final JButton buttonMore1 = new JButton("");
		final JButton buttonMore2 = new JButton("");
		buttonMinus.setEnabled(false);
		buttonMinus1.setEnabled(false);
		buttonMinus2.setEnabled(false);
		
		final JButton buttonReset = new JButton("Reiniciar");
		ImageIcon icono_reiniciar = new ImageIcon("recursos//botonReiniciar.png");
		buttonReset.setIcon(icono_reiniciar);
		buttonReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {;
				puntosAsignarInicial=puntosAsignarBase;
				puntosAsignar=puntosAsignarInicial;
				
				puntosFuerzaInicial=puntosFuerzaBase;
				puntosDestrezaInicial=puntosDestrezaBase;
				puntosInteligenciaInicial=puntosInteligenciaBase;
				
				puntosFuerza = puntosFuerzaInicial;
				puntosDestreza = puntosDestrezaInicial;
				puntosInteligencia = puntosInteligenciaInicial;

				labelFuerza.setText(String.valueOf(puntosFuerzaInicial));
				labelDestreza.setText(String.valueOf(puntosDestrezaInicial));
				labelInteligencia.setText(String.valueOf(puntosInteligenciaInicial));
				labelPuntos.setText(String.valueOf(puntosAsignarInicial));
				
				buttonMinus.setEnabled(false);
				buttonMinus1.setEnabled(false);
				buttonMinus2.setEnabled(false);
				if(puntosAsignar!=0) {
					buttonMore.setEnabled(true);
					buttonMore1.setEnabled(true);
					buttonMore2.setEnabled(true);	
				}
			}
		});
		buttonReset.setBounds(176, 180, 97, 25);
		contentPane.add(buttonReset);
		
		final JButton buttonCancel = new JButton("Cancelar");
		ImageIcon icono_c = new ImageIcon("recursos//botonCancelar.png");
		buttonCancel.setIcon(icono_c);
		buttonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Pantalla.cerrarMenu(Pantalla.menuAsignar);
				dispose();
			}
		});
		buttonCancel.setBounds(176, 146, 97, 25);
		contentPane.add(buttonCancel);
		
		if(puntosAsignarInicial==0) {
			buttonMore.setEnabled(false);
			buttonMore1.setEnabled(false);
			buttonMore2.setEnabled(false);
		}
		
		ImageIcon icono_1 = new ImageIcon("recursos//botonMenoss.png");
		buttonMinus.setIcon(icono_1);
		buttonMinus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(puntosFuerza > puntosFuerzaInicial){
					puntosFuerza--;
						if(puntosInteligencia != 200){
							buttonMore2.setEnabled(true);
						}
						if(puntosDestreza != 200){
							buttonMore1.setEnabled(true);
						}
					puntosAsignar++;
					labelPuntos.setText(String.valueOf(puntosAsignar));
					labelFuerza.setText(String.valueOf(puntosFuerza));
					if(puntosFuerza == puntosFuerzaInicial)
						buttonMinus.setEnabled(false);
					buttonMore.setEnabled(true);
				}
			}
		});
		buttonMinus.setBounds(12, 92, 34, 25);
		contentPane.add(buttonMinus);
		
		buttonMinus1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(puntosDestreza > puntosDestrezaInicial){
					puntosDestreza--;
						if(puntosInteligencia != 200){
							buttonMore2.setEnabled(true);
						}
						if(puntosFuerza != 200){
							buttonMore.setEnabled(true);
						}
					puntosAsignar++;
					labelPuntos.setText(String.valueOf(puntosAsignar));
					labelDestreza.setText(String.valueOf(puntosDestreza));
					if(puntosDestreza == puntosDestrezaInicial)
						buttonMinus1.setEnabled(false);
					buttonMore1.setEnabled(true);

				}
			}
		});
		buttonMinus1.setIcon(icono_1);
		buttonMinus1.setBounds(12, 159, 34, 25);
		contentPane.add(buttonMinus1);
		
		buttonMinus2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(puntosInteligencia > puntosInteligenciaInicial){
					puntosInteligencia--;
						if(puntosFuerza != 200){
							buttonMore.setEnabled(true);
						}
						if(puntosDestreza != 200){
							buttonMore1.setEnabled(true);
						}
					puntosAsignar++;
					labelPuntos.setText(String.valueOf(puntosAsignar));
					labelInteligencia.setText(String.valueOf(puntosInteligencia));
					if(puntosInteligencia == puntosInteligenciaInicial)
						buttonMinus2.setEnabled(false);
					buttonMore2.setEnabled(true);
				}
			}
		});
		buttonMinus2.setIcon(icono_1);
		buttonMinus2.setBounds(12, 217, 34, 25);
		contentPane.add(buttonMinus2);
		
		buttonMore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				puntosFuerza++;
				puntosAsignar--;
				labelPuntos.setText(String.valueOf(puntosAsignar));
				labelFuerza.setText(String.valueOf(puntosFuerza));
				buttonMinus.setEnabled(true);
				if(puntosAsignar == 0){
						buttonMore.setEnabled(false);
						buttonMore1.setEnabled(false);
						buttonMore2.setEnabled(false);
					}
				if(puntosFuerza == 200){
					buttonMore.setEnabled(false);
				}
			}
		});
		ImageIcon icono_2 = new ImageIcon("recursos//botonMass.png");
		buttonMore.setIcon(icono_2);
		buttonMore.setBounds(118, 92, 34, 25);
		contentPane.add(buttonMore);
		
		
		buttonMore1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				puntosDestreza++;
				puntosAsignar--;
				labelPuntos.setText(String.valueOf(puntosAsignar));
				labelDestreza.setText(String.valueOf(puntosDestreza));
				buttonMinus1.setEnabled(true);
				if(puntosAsignar == 0){
						buttonMore.setEnabled(false);
						buttonMore1.setEnabled(false);
						buttonMore2.setEnabled(false);
				}
				if(puntosDestreza==200){
					buttonMore1.setEnabled(false);
				}
			}
		});
		buttonMore1.setIcon(icono_2);
		buttonMore1.setBounds(118, 159, 34, 25);
		contentPane.add(buttonMore1);
		
		buttonMore2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				puntosInteligencia++;
				puntosAsignar--;
				labelPuntos.setText(String.valueOf(puntosAsignar));
				labelInteligencia.setText(String.valueOf(puntosInteligencia));					
				buttonMinus2.setEnabled(true);
				if(puntosAsignar == 0){
						buttonMore.setEnabled(false);
						buttonMore1.setEnabled(false);
						buttonMore2.setEnabled(false);
				}
				if(puntosInteligencia==200){
					buttonMore2.setEnabled(false);
				}
			}
		});
		buttonMore2.setIcon(icono_2);
		buttonMore2.setBounds(118, 217, 34, 25);
		contentPane.add(buttonMore2);
		
		final JLabel imageLabel = new JLabel(new ImageIcon("recursos//background.jpg")); 
		imageLabel.setBounds(0, 0, 298, 294);
		imageLabel.setVisible(true);
		contentPane.add(imageLabel);
	}
}
