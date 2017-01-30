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
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class ViewArmadorEquipos extends GenericForm{

	
	public DefaultListModel<Jugador> lmodJugadoresTotales = new DefaultListModel();
	
	public DefaultListModel<Jugador> jugadoresEq1lmod = new DefaultListModel();
	public DefaultListModel<Jugador> jugadoresEq2lmod = new DefaultListModel();
	
	private ArmadorEquipos armador = new ArmadorEquipos();
	
	private JFrame frame;

	private JList listaJugadores;
	private JLabel lbHablility1 = new JLabel("0");		
	private JLabel lbHablility2 = new JLabel("0");

	private JList listE1 ;

	private JList listE2;
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
		frame.setVisible(true);
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
		frame.getContentPane().setBackground(new Color(0, 204, 0));
		frame.setResizable(false);
		frame.setBounds(100, 100, 460, 488);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		listaJugadores = new JList(lmodJugadoresTotales);  
		listaJugadores.setBackground(new Color(255, 255, 204));
		listaJugadores.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		listaJugadores.setBounds(38, 38, 370, 53);
		frame.getContentPane().add(listaJugadores);
		listaJugadores.setVisibleRowCount(2);
		
		 listE1 = new JList(jugadoresEq1lmod);
		 listE1.setBackground(new Color(255, 255, 204));
		listE1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				devolverJugador(listE1,jugadoresEq1lmod,lbHablility1);
				
			}
		});
		listE1.setBounds(23, 200, 143, 133);
		frame.getContentPane().add(listE1);
		
		listE2 = new JList(jugadoresEq2lmod);
		listE2.setBackground(new Color(255, 255, 204));
		listE2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				devolverJugador(listE2,jugadoresEq2lmod,lbHablility2);
			}
		});
		listE2.setBounds(287, 200, 134, 133);
		frame.getContentPane().add(listE2);
		
		JLabel lblEquipo = new JLabel("Equipo 1");
		lblEquipo.setFont(new Font("Viner Hand ITC", Font.BOLD, 19));
		lblEquipo.setBounds(40, 164, 92, 26);
		frame.getContentPane().add(lblEquipo);
		
		JLabel lblEquipo_2 = new JLabel("Equipo 2");
		lblEquipo_2.setFont(new Font("Viner Hand ITC", Font.BOLD, 19));
		lblEquipo_2.setBounds(320, 164, 92, 26);
		frame.getContentPane().add(lblEquipo_2);
		
		JLabel lblJugadores = new JLabel("Jugadores");
		lblJugadores.setFont(new Font("Viner Hand ITC", Font.BOLD, 19));
		lblJugadores.setBounds(188, 11, 92, 26);
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
		btnGenerarEquipos.setBounds(152, 373, 143, 36);
		frame.getContentPane().add(btnGenerarEquipos);
		
		//Agregar E1
		JButton btnAgregarE1 = new JButton("Agregar");
		btnAgregarE1.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent arg0) {
				  agregarJugadores(jugadoresEq1lmod,lbHablility1);
			}
		});
		btnAgregarE1.setBounds(71, 125, 89, 23);
		frame.getContentPane().add(btnAgregarE1);
		
		JButton btnAgregarE2 = new JButton("Agregar");
		btnAgregarE2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 agregarJugadores(jugadoresEq2lmod, lbHablility2);
			}
		});
		btnAgregarE2.setBounds(298, 125, 89, 23);
		frame.getContentPane().add(btnAgregarE2);
		

		lbHablility1.setBounds(71, 344, 46, 14);
		frame.getContentPane().add(lbHablility1);
		

		lbHablility2.setBounds(353, 344, 46, 14);
		frame.getContentPane().add(lbHablility2);
		
		JButton button = new JButton("");
		button.setIcon(new ImageIcon("backIcon35.png"));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				volver();
			}
		});
		button.setBounds(23, 401, 60, 44);
		frame.getContentPane().add(button);
		
		JButton btnRedo = new JButton("");
		btnRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				devolverTodos();
			}
		});
		btnRedo.setIcon(new ImageIcon("redoIcon30.png"));
		btnRedo.setBounds(198, 229, 46, 44);
		frame.getContentPane().add(btnRedo);
		
		JLabel lbBall = new JLabel("");
		lbBall.setIcon(new ImageIcon("soccer_ball50.png"));
		lbBall.setBounds(198, 148, 46, 42);
		frame.getContentPane().add(lbBall);
		
	//	modelo.main.cargarJugadoresFacu().forEach(j ->lmodJugadoresTotales.addElement(j));
		
	}


	protected void volver() {
		ViewMain v= (ViewMain) this.getFormAnterior();
		v.iniciar();
		this.frame.dispose();
	}

	private ArrayList<Jugador> toListJugador(DefaultListModel<Jugador> jugadores) {
		ArrayList<Jugador> participantes = new ArrayList<Jugador>();
		for(int i = 0;i<jugadores.size();i++){
			participantes.add(jugadores.getElementAt(i));
		}
		return participantes;
	}

	private void agregarJugadores(DefaultListModel<Jugador> listToAdd, JLabel lbHablility) {
		int index = listaJugadores.getSelectedIndex(); 
		    if (index == -1) { //no selection, so insert at beginning
		        return;
		    }	
		 Jugador j = lmodJugadoresTotales.getElementAt(index);
		listToAdd.addElement((Jugador)listaJugadores.getSelectedValue());
		lmodJugadoresTotales.remove(index);
		lbHablility.setText(String.valueOf(Integer.parseInt(lbHablility.getText())+j.getHability()));
	}

	public void setParticipantes(DefaultListModel<Jugador> lmod) {
	//lmodJugadoresTotales= lmod;
	 for(int i = 0;i<lmod.size();i++){
		 lmodJugadoresTotales.addElement(lmod.get(i));
		}
	}

	public void devolverJugador(JList list, DefaultListModel<Jugador> lmod, JLabel lbHablility) {
		int index = list.getSelectedIndex();
		if(index==-1)index=0;
		Jugador j = lmod.getElementAt(index);
		lbHablility.setText(String.valueOf(Integer.parseInt(lbHablility.getText())-j.getHability()));
		lmodJugadoresTotales.addElement(j);
		lmod.remove(index);
	}
	protected void devolverTodos() {
		int size1 = jugadoresEq1lmod.size();
		for(int i = 0; i<size1;i++){
			devolverJugador(listE1, jugadoresEq1lmod, lbHablility1);
		}
		int size2 = jugadoresEq2lmod.size();
		for(int i = 0; i<size2;i++){
			devolverJugador(listE2, jugadoresEq2lmod, lbHablility2);
		}
		
	}

}
