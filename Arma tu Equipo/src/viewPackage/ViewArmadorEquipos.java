package viewPackage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Window.Type;
import javax.swing.JList;
import java.awt.BorderLayout;
import net.miginfocom.swing.MigLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import modelo.ArmadorEquipos;
import modelo.Jugador;
import modelo.Match;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.awt.event.ActionEvent;

public class ViewArmadorEquipos {

	
	public DefaultListModel<Jugador> lmodJugadoresTotales = new DefaultListModel();
	
	public DefaultListModel<Jugador> jugadoresEq1lmod = new DefaultListModel();
	public DefaultListModel<Jugador> jugadoresEq2lmod = new DefaultListModel();
	
	private ArmadorEquipos armador = new ArmadorEquipos();
	
	private JFrame frame;

	private JList listaJugadores;
	private JLabel lbHablility1 = new JLabel("0");		
	private JLabel lbHablility2 = new JLabel("0");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewArmadorEquipos window = new ViewArmadorEquipos();
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
	public ViewArmadorEquipos() {
		initialize();
	}

	public ViewArmadorEquipos(DefaultListModel<Jugador> lmod) {
		initialize();
		setParticipantes(lmod);
		this.frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 503, 484);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		listaJugadores = new JList(lmodJugadoresTotales);  
		listaJugadores.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		listaJugadores.setBounds(72, 32, 345, 53);
		frame.getContentPane().add(listaJugadores);
		listaJugadores.setVisibleRowCount(2);
		
		JList listE1 = new JList(jugadoresEq1lmod);
		listE1.setBounds(57, 194, 143, 133);
		frame.getContentPane().add(listE1);
		
		JList listE2 = new JList(jugadoresEq2lmod);
		listE2.setBounds(321, 194, 134, 133);
		frame.getContentPane().add(listE2);
		
		JLabel lblEquipo = new JLabel("Equipo 1");
		lblEquipo.setFont(new Font("Viner Hand ITC", Font.BOLD, 19));
		lblEquipo.setBounds(74, 158, 92, 26);
		frame.getContentPane().add(lblEquipo);
		
		JLabel lblEquipo_2 = new JLabel("Equipo 2");
		lblEquipo_2.setFont(new Font("Viner Hand ITC", Font.BOLD, 19));
		lblEquipo_2.setBounds(354, 158, 92, 26);
		frame.getContentPane().add(lblEquipo_2);
		
		JLabel lblJugadores = new JLabel("Jugadores");
		lblJugadores.setFont(new Font("Viner Hand ITC", Font.BOLD, 19));
		lblJugadores.setBounds(222, 5, 92, 26);
		frame.getContentPane().add(lblJugadores);
		
		JButton btnGenerarEquipos = new JButton("Generar Equipos");
		btnGenerarEquipos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				ArrayList<Jugador> participantes = toListJugador(lmodJugadoresTotales);
		
				 ArrayList<Jugador> jugadoresInmutable =  new 	ArrayList<Jugador>();
				 jugadoresInmutable.addAll(toListJugador(jugadoresEq1lmod));
				 jugadoresInmutable.addAll(toListJugador(jugadoresEq2lmod));
				 jugadoresInmutable.addAll(participantes);
				 
				 ArrayList<Match> m = armador.armarEquipoConJugadores(jugadoresInmutable, toListJugador(jugadoresEq1lmod), toListJugador(jugadoresEq2lmod));
			
				 jugadoresEq1lmod.removeAllElements();
				 jugadoresEq2lmod.removeAllElements();
				 
				 Match match = m.get(0);
				ArrayList<Jugador> jugadoresFinalesE1 = match.getJugadoresEquipo1();
				
				 for(int i = 0;i<jugadoresFinalesE1.size();i++){
					jugadoresEq1lmod.addElement(jugadoresFinalesE1.get(i));
				}
				
				 ArrayList<Jugador> jugadoresFinalesE2 = match.getJugadoresEquipo2();
				 
				for(int i = 0;i<jugadoresFinalesE2.size();i++){
					jugadoresEq2lmod.addElement(match.getJugadoresEquipo2().get(i));
				}
		
				lmodJugadoresTotales.removeAllElements();
				lbHablility1.setText(String.valueOf(match.getHability(1)));
				lbHablility2.setText(String.valueOf(match.getHability(2)));
			}
		});
		btnGenerarEquipos.setBounds(186, 367, 143, 36);
		frame.getContentPane().add(btnGenerarEquipos);
		
		//Agregar E1
		JButton btnAgregarE1 = new JButton("Agregar");
		btnAgregarE1.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent arg0) {
				  agregarJugadores(jugadoresEq1lmod);
			}
		});
		btnAgregarE1.setBounds(72, 119, 89, 23);
		frame.getContentPane().add(btnAgregarE1);
		
		JButton btnAgregarE2 = new JButton("Agregar");
		btnAgregarE2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 agregarJugadores(jugadoresEq2lmod);
			}
		});
		btnAgregarE2.setBounds(354, 119, 89, 23);
		frame.getContentPane().add(btnAgregarE2);
		

		lbHablility1.setBounds(114, 338, 46, 14);
		frame.getContentPane().add(lbHablility1);
		

		lbHablility2.setBounds(396, 338, 46, 14);
		frame.getContentPane().add(lbHablility2);
		
	//	modelo.main.cargarJugadoresFacu().forEach(j ->lmodJugadoresTotales.addElement(j));
		
	}

	private ArrayList<Jugador> toListJugador(DefaultListModel<Jugador> jugadores) {
		ArrayList<Jugador> participantes = new ArrayList<Jugador>();
		for(int i = 0;i<jugadores.size();i++){
			participantes.add(jugadores.getElementAt(i));
		}
		return participantes;
	}

	private void agregarJugadores(DefaultListModel<Jugador> listToAdd) {
		int index = listaJugadores.getSelectedIndex(); 
		    if (index == -1) { //no selection, so insert at beginning
		        return;
		    }	
		listToAdd.addElement((Jugador)listaJugadores.getSelectedValue());
		lmodJugadoresTotales.remove(index);//(listaJugadores.getSelectedValue());
	}

	public void setParticipantes(DefaultListModel<Jugador> lmod) {
	//lmodJugadoresTotales= lmod;
	 for(int i = 0;i<lmod.size();i++){
		 lmodJugadoresTotales.addElement(lmod.get(i));
		}
	}
}
