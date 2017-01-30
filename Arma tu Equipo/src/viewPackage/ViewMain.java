package viewPackage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;

public class ViewMain extends GenericForm{

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewMain window = new ViewMain();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void iniciar() {
		frame.setVisible(true);
	}
	public ViewMain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		Color backColor = this.getColor();
		frame.getContentPane().setBackground(backColor);
		frame.setResizable(false);
		frame.setBounds(100, 100, 455, 359);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnCargarJugadores = new JButton("Cargar Jugadores");
		btnCargarJugadores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadPlayers();
			}
		});
		btnCargarJugadores.setFont(new Font("Stencil", Font.PLAIN, 13));
		btnCargarJugadores.setBounds(112, 54, 228, 78);
		frame.getContentPane().add(btnCargarJugadores);
		
		JButton btnGenerarEquipo = new JButton("Generar Equipo");
		btnGenerarEquipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generarEquipo();
			}
		});
		btnGenerarEquipo.setFont(new Font("Stencil", Font.PLAIN, 13));
		btnGenerarEquipo.setBounds(112, 169, 228, 78);
		frame.getContentPane().add(btnGenerarEquipo);
		
		JButton btnSetUp = new JButton("");
		btnSetUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				irAConfiguracion();
			}
		});
		btnSetUp.setIcon(new ImageIcon("setUpIcon40.png"));
		btnSetUp.setBounds(139, 270, 42, 39);
		frame.getContentPane().add(btnSetUp);
	}

	protected void irAConfiguracion() {
		Configuracion view = new Configuracion();
		view.setFormAnterior((GenericForm)this);
		this.frame.setVisible(false);
	}

	public void generarEquipo() {
		ViewPlayerSelection view = new ViewPlayerSelection();
		view.setFormAnterior((GenericForm)this);
	}

	private void loadPlayers() {
		ViewAbmPlayers view = new ViewAbmPlayers();
		view.setFormAnterior((GenericForm)this);
	}
	/*private <T> void irA(Class<T> v) {
		T myView = v.newInstance();
		((GenericForm) myView).setFormAnterior((GenericForm)this);
		(GenericForm) myView).main(null);
	}*/

	public void visible() {
		frame.setVisible(true);
	}
}
