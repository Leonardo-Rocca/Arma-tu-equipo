package viewPackage;
import modelo.*;
import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.AbstractListModel;
import java.lang.Package;

public class ViewAbmPlayers {

	private JFrame frame;
	private JTextField txtNombre;
	private JTextField txtHabilidad;
	private final JLabel lblHabilidad = new JLabel("Habilidad");


	
	public DefaultListModel<Jugador> lmod = new DefaultListModel();
	private JList list = new JList(lmod);  //<-aca está el tema
	
	private ArrayList<Jugador> jugadores =	new ArrayList<Jugador>();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewAbmPlayers window = new ViewAbmPlayers();
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
	public ViewAbmPlayers() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	     lmod.addElement(new Jugador("Leo",9));
	     

		list.setModel(new AbstractListModel() {
			String[] values = new String[] {};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
//		ArrayList<Jugador> jugadores =	new ArrayList<Jugador>();
		
		//JList 
		list = new JList(lmod);
		list.setVisibleRowCount(5);
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL_WRAP);
	
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(300, 47, 124, 42);
		btnAgregar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				String name = txtNombre.getText();
				String hability = txtHabilidad.getText();
				
				  if (name.equals("") || hability.equals("") ) {
				      
					  txtNombre.requestFocusInWindow();
					  txtNombre.selectAll();
				        return;
				    }
				  int index = list.getSelectedIndex(); //get selected index
				    if (index == -1) { //no selection, so insert at beginning
				        index = 0;
				    } else {           //add after the selected item
				        index++;
				    }
				  
				//jugadores.add(new Jugador(txtNombre.getText(), Integer.parseInt(txtHabilidad.getText())));
			
				lmod.insertElementAt(new Jugador(txtNombre.getText(), Integer.parseInt(txtHabilidad.getText())), index);
			
			//	lmod.addElement(new Jugador(txtNombre.getText(), Integer.parseInt(txtHabilidad.getText())));
			//	list = new JList<Jugador>(lmod);
				
			    //Reset the text field.
				txtNombre.requestFocusInWindow();
				txtNombre.setText("");

			    //Select the new item and make it visible.
			    list.setSelectedIndex(index);
			    list.ensureIndexIsVisible(index);
				
			}
		});
		frame.getContentPane().setLayout(null);
		
		JLabel lblJugadores = new JLabel("Jugadores");
		lblJugadores.setBounds(149, 0, 71, 12);
		frame.getContentPane().add(lblJugadores);
		

		
		txtNombre = new JTextField();
		txtNombre.setBounds(10, 53, 94, 25);
		txtNombre.setText("Nombre");
		frame.getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		//JList list = new JList();
		list.setBounds(95, 119, 227, 95);
		frame.getContentPane().add(list);
		frame.getContentPane().add(btnAgregar);
		
		txtHabilidad = new JTextField();
		txtHabilidad.setBounds(147, 53, 59, 25);
		txtHabilidad.setText("5");
		frame.getContentPane().add(txtHabilidad);
		txtHabilidad.setColumns(10);
		lblHabilidad.setBounds(154, 23, 136, 31);
		frame.getContentPane().add(lblHabilidad);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ViewArmadorEquipos armador = new	ViewArmadorEquipos(lmod);
			//	armador.main(null);
			//	armador.setParticipantes(lmod);
			}
		});
		btnOk.setBounds(313, 225, 94, 26);
		frame.getContentPane().add(btnOk);
	}
}
