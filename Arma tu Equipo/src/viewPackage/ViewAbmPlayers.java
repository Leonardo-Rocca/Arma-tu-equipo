package viewPackage;
import modelo.*;
import persistencia.FileSystem;

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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.AbstractListModel;
import java.lang.Package;
import java.lang.reflect.GenericSignatureFormatError;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ViewAbmPlayers extends GenericForm {

	private JFrame frame;
	private JTextField txtNombre;
	private JTextField txtHabilidad;
	private final JLabel lblHabilidad = new JLabel("Habilidad");


	
	public DefaultListModel<Jugador> lmod = new DefaultListModel();
	private JList list = new JList(lmod);  //<-aca está el tema
	
	private ArrayList<Jugador> jugadores =	new ArrayList<Jugador>();

	
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
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 422, 354);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		list = new JList(lmod);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int index = list.getSelectedIndex();
				Jugador j = lmod.getElementAt(index);
				txtNombre.setText(j.getName());
				txtHabilidad.setText(String.valueOf(j.getHability()));
				txtHabilidad.requestFocusInWindow();
				txtHabilidad.selectAll();
				lmod.remove(index);
			}
		});
		list.setVisibleRowCount(5);
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL_WRAP);
	
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(292, 45, 115, 40);
		btnAgregar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				agregar();
				
			}
		});
		frame.getContentPane().setLayout(null);
		
		JLabel lblJugadores = new JLabel("Jugadores");
		lblJugadores.setBounds(149, 0, 71, 12);
		frame.getContentPane().add(lblJugadores);
		

		
		txtNombre = new JTextField();
		txtNombre.setBounds(10, 53, 94, 25);
		frame.getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		//JList list = new JList();
		list.setBounds(75, 118, 215, 130);
		frame.getContentPane().add(list);
		frame.getContentPane().add(btnAgregar);
		
		txtHabilidad = new JTextField();
		txtHabilidad.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				 if(arg0.getKeyCode() == KeyEvent.VK_ENTER) {
				     agregar();
				   }
			}
		});
		txtHabilidad.setBounds(147, 53, 59, 25);
		frame.getContentPane().add(txtHabilidad);
		txtHabilidad.setColumns(10);
		lblHabilidad.setBounds(154, 23, 78, 31);
		frame.getContentPane().add(lblHabilidad);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				persistir();
				regresar();
			}
		});
		btnOk.setBounds(289, 266, 94, 26);
		frame.getContentPane().add(btnOk);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(21, 23, 136, 31);
		frame.getContentPane().add(lblNombre);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				regresar();
			}
		});
		btnCancelar.setBounds(33, 268, 94, 26);
		frame.getContentPane().add(btnCancelar);
		
		JLabel lblSeleccioneJugadorPara = new JLabel("Seleccione jugador para modificar/eliminar");
		lblSeleccioneJugadorPara.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblSeleccioneJugadorPara.setBounds(20, 92, 251, 31);
		frame.getContentPane().add(lblSeleccioneJugadorPara);
		
		this.setParticipantes(new FileSystem().getListPlayers());
	}

	protected void persistir() {
		new FileSystem().persistListPlayers(lmod);	
	}

	private void setParticipantes(ArrayList<Jugador> listPlayers) {
			 for(int i = 0;i<listPlayers.size();i++){
				 lmod.addElement(listPlayers.get(i));
				}
			}

	public void agregar() {
		String name = txtNombre.getText();
		String hability = txtHabilidad.getText();
		
		  if (name.equals("") || hability.equals("") ) {
		      
			  txtNombre.requestFocusInWindow();
			  txtNombre.selectAll();
		        return;
		    }

		    try
		    {
		        int actualValue = Integer.parseInt(txtHabilidad.getText());

		    }
		    catch (NumberFormatException e)
		    {
		    	  JOptionPane.showMessageDialog(new JPanel(), "Debe ingresar un Número Entero", "Error", JOptionPane.ERROR_MESSAGE);
				  txtHabilidad.requestFocusInWindow();
				  txtHabilidad.setText("");
		        return;
		    }
	lmod.addElement(new Jugador(txtNombre.getText(), Integer.parseInt(hability)));
		
		//Reset the text field.
		txtNombre.requestFocusInWindow();
		txtNombre.setText("");
		txtHabilidad.setText("");
	}

	public void regresar() {
		//ViewArmadorEquipos armador = new	ViewArmadorEquipos(lmod);
		ViewMain f = (ViewMain) getFormAnterior();
		f.iniciar();
		this.frame.dispose();
	}
}
