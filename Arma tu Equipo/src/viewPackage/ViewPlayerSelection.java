package viewPackage;

import java.awt.Checkbox;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JFrame;

import modelo.Jugador;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewPlayerSelection {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewPlayerSelection window = new ViewPlayerSelection();
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
	public ViewPlayerSelection() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 451, 451);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		CheckBoxList chkPlayers = new CheckBoxList();
		ArrayList<Jugador> jugadoresTotales = modelo.main.cargarJugadoresFacu();
		int tamanio = jugadoresTotales.size();
		JCheckBox[] ch = new JCheckBox[tamanio];//   <--- modificar pa previsualizar
		//JCheckBox[] ch ={ new JCheckBox("dsd")};
		
		for(int i =0; i<tamanio;i++){
			ch[i] = new JCheckBox(jugadoresTotales.get(i).toString());
		}
	
		chkPlayers.setListData(ch);
		chkPlayers.setBounds(136, 61, 161, 247);

		frame.getContentPane().add(chkPlayers);
		
		JLabel lblSeleccioneLosJugadores = new JLabel("Seleccione los jugadores");
		lblSeleccioneLosJugadores.setFont(new Font("Stencil", Font.PLAIN, 16));
		lblSeleccioneLosJugadores.setBounds(120, 11, 238, 31);
		frame.getContentPane().add(lblSeleccioneLosJugadores);
		
		JButton btnComenzar = new JButton("Comenzar");
		btnComenzar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ViewArmadorEquipos armador = new	ViewArmadorEquipos(defaultListJugadores());
			}
			private DefaultListModel<Jugador> defaultListJugadores() {
				DefaultListModel<Jugador> participantes = new DefaultListModel<Jugador>();
				for(int i =0; i<tamanio;i++){
					if(ch[i].isSelected()) participantes.addElement(jugadoresTotales.get(i));
				}
				return participantes;
			}
		});
		btnComenzar.setBounds(274, 343, 129, 31);
		frame.getContentPane().add(btnComenzar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(35, 343, 129, 31);
		frame.getContentPane().add(btnVolver);

	}

}
