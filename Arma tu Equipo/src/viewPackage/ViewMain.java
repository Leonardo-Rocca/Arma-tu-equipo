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

	/**
	 * Create the application.
	 */
	public ViewMain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 406, 359);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnCargarJugadores = new JButton("Cargar Jugadores");
		btnCargarJugadores.setFont(new Font("Stencil", Font.PLAIN, 13));
		btnCargarJugadores.setBounds(83, 54, 228, 78);
		frame.getContentPane().add(btnCargarJugadores);
		
		JButton btnGenerarEquipo = new JButton("Generar Equipo");
		btnGenerarEquipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generarEquipo();
			}
		});
		btnGenerarEquipo.setFont(new Font("Stencil", Font.PLAIN, 13));
		btnGenerarEquipo.setBounds(83, 169, 228, 78);
		frame.getContentPane().add(btnGenerarEquipo);
	}

	public void generarEquipo() {
		ViewPlayerSelection view = new ViewPlayerSelection();
		view.setFormAnterior((GenericForm)this);
		view.main(null);
	}
}
