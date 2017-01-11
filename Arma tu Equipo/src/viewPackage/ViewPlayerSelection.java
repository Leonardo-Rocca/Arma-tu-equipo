package viewPackage;

import java.awt.Checkbox;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JFrame;

import modelo.Jugador;
import persistencia.FileSystem;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class ViewPlayerSelection extends GenericForm{

	private JFrame frame;

	private boolean allSelected = false;
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
		frame.setVisible(true);
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
		ArrayList<Jugador> jugadoresTotales =  new FileSystem().getListPlayers();//modelo.main.cargarJugadoresFacu();

		int tamanio = jugadoresTotales.size();
	    JCheckBox[] ch = new JCheckBox[tamanio];//   <--- modificar pa previsualizar
	//	JCheckBox[] ch ={ new JCheckBox("dsd")};
		
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
			//	wrapSetForm(armador);
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
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				volver();
			}

		});
		btnVolver.setBounds(35, 343, 129, 31);
		frame.getContentPane().add(btnVolver);
		
		JButton btnSeleccionarTodos = new JButton("");
		btnSeleccionarTodos.setIcon(new ImageIcon("tick2.jpg"));
		btnSeleccionarTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectAll(ch);
			}

			private void selectAll(JCheckBox[] ch) {
				for(int i =0; i<ch.length;i++){
					ch[i].setSelected(!allSelected);
				}
				chkPlayers.repaint();
				allSelected=!allSelected;
			}
		});
		btnSeleccionarTodos.setFont(new Font("Simplified Arabic Fixed", Font.PLAIN, 10));
		btnSeleccionarTodos.setBounds(329, 217, 39, 38);
		frame.getContentPane().add(btnSeleccionarTodos);
		
		JLabel lblSeleccionar = new JLabel("Seleccionar");
		lblSeleccionar.setBounds(329, 164, 91, 31);
		frame.getContentPane().add(lblSeleccionar);
		
		JLabel lblTodos = new JLabel("Todos");
		lblTodos.setBounds(329, 194, 91, 21);
		frame.getContentPane().add(lblTodos);

	}

	protected void volver() {
		ViewMain m = (ViewMain) this.getFormAnterior();
		m.iniciar();
		this.frame.dispose();
	}

	public void generarEquipo() {
		this.getFormAnterior();
	}

	private void wrapSetForm(ViewArmadorEquipos armador) {
		armador.setFormAnterior(this.getFormAnterior());
	}

}
