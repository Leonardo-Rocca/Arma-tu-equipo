package viewPackage;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;

import persistencia.PersistidorGrups;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Configuracion extends GenericForm{

	private final PersistidorGrups persistidor = new PersistidorGrups();
	private JFrame frame;
	private JComboBox comboBox;
	private DefaultComboBoxModel<String> cmod;
	private JLabel lblGrupoActual;
	private JButton btnCambiar;
	private JTextField txtNewGroup;
	private JButton button;
	private JLabel lblIngreseNombreDe;
	private JLabel lbInformation;
	private JLabel lblFondoinfo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Configuracion window = new Configuracion();
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
	public Configuracion() {
		initialize();
		this.frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		cmod = new DefaultComboBoxModel<String>(this.toArrayString((persistidor.getGroups())));
				
				lblFondoinfo = new JLabel("");
				lblFondoinfo.setBounds(80, 11, 223, 232);
				lblFondoinfo.setIcon(new ImageIcon("imagenes\\cesped3.jpg"));
				lblFondoinfo.setVisible(false);
				
				//lbInformation = this.cargarFondo2();// new JLabel("Creado por Leonardo Rocca\r\nParticipaci\u00F3n de Eric K.");
				lbInformation =new JLabel("Creado por Leonardo Rocca");
				lbInformation.setForeground(Color.WHITE);
				lbInformation.setFont(new Font("Tahoma", Font.BOLD, 11));
				//	lbInformation.setText("Creado por Leonardo Rocca\r\nParticipaci\u00F3n de Eric K.");
					

				lbInformation.setBounds(110, 83, 182, 51);
				lbInformation.setVisible(false);
				frame.getContentPane().add(lbInformation);
				frame.getContentPane().add(lblFondoinfo);
				
		comboBox = new JComboBox(cmod);
		comboBox.setBounds(34, 67, 208, 20);
		frame.getContentPane().add(comboBox);
		
		JButton btnVolver = new JButton("");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				volver();
			}
		});
		btnVolver.setIcon(new ImageIcon("backIcon35.png"));
		btnVolver.setBounds(38, 194, 57, 37);
		frame.getContentPane().add(btnVolver);
		
		lblGrupoActual = new JLabel("Grupo actual: ");
		lblGrupoActual.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblGrupoActual.setForeground(new Color(255, 255, 255));
		lblGrupoActual.setBounds(24, 27, 265, 20);
		frame.getContentPane().add(lblGrupoActual);
		
		btnCambiar = new JButton("Cambiar");
		btnCambiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cambiarFile();
			}
		});
		btnCambiar.setBounds(274, 61, 101, 32);
		frame.getContentPane().add(btnCambiar);
		
		txtNewGroup = new JTextField();
		txtNewGroup.setToolTipText("Nuevo grupo");
		txtNewGroup.setBounds(38, 153, 204, 20);
		frame.getContentPane().add(txtNewGroup);
		txtNewGroup.setColumns(10);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crearGrupo();
			}
		});
		btnAgregar.setBounds(290, 147, 101, 32);
		frame.getContentPane().add(btnAgregar);
		
		button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminar();
			}
		});
		button.setIcon(new ImageIcon(Configuracion.class.getResource("/com/sun/javafx/scene/control/skin/caspian/dialog-error.png")));
		button.setBounds(385, 61, 32, 32);
		frame.getContentPane().add(button);
		lblGrupoActual.setText(lblGrupoActual.getText() + persistidor.getFilePointed());
		
		lblIngreseNombreDe = new JLabel("Ingrese nombre de grupo(sin espacios)");
		lblIngreseNombreDe.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblIngreseNombreDe.setForeground(new Color(255, 255, 255));
		lblIngreseNombreDe.setBounds(34, 131, 341, 20);
		frame.getContentPane().add(lblIngreseNombreDe);

		
		JLabel lblInfo = new JLabel("");
		lblInfo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
			    lbInformation.setVisible(true);
				lblFondoinfo.setVisible(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				   lbInformation.setVisible(false);
				lblFondoinfo.setVisible(false);
			}
		});
		lblInfo.setIcon(new ImageIcon(Configuracion.class.getResource("/com/sun/javafx/scene/control/skin/modena/HTMLEditor-Justify-White@2x.png")));
		lblInfo.setBounds(345, 209, 46, 45);
		frame.getContentPane().add(lblInfo);
		frame.getContentPane().add(cargarFondo());
		

		
	}



	protected void cambiarFile() {
		persistidor.persistFilePointed(cmod.getElementAt(comboBox.getSelectedIndex()));	
		lblGrupoActual.setText("Grupo actual: "+ persistidor.getFilePointed());
	}

	protected void eliminar() {
		if(cmod.getSize()==1)return;
		int i = comboBox.getSelectedIndex();
		cmod.removeElementAt(i);
		persistidor.persistGroups(this.toListString(cmod));
	}

	private String[] toArrayString(ArrayList<String> arrayList) {
		String[] s =new String[arrayList.size()];
		for(int i =0;i<arrayList.size();i++){
			s[i] = arrayList.get(i);
		}
		return s;
	}

	protected void volver() {
		((ViewMain)this.getFormAnterior()).visible();
	this.frame.dispose();
	}

	public void crearGrupo() {
		if(txtNewGroup.equals("")) return;
		
		  Pattern pat = Pattern.compile("[a-zA-Z0-9-]{1,}");
		     Matcher mat = pat.matcher(txtNewGroup.getText());
		     if (mat.matches()) {
		 		cmod.addElement(txtNewGroup.getText()+".file");
				persistidor.persistGroups(this.toListString(cmod));
				persistidor.createFile(txtNewGroup.getText());
				txtNewGroup.setText("");
		     } else {
		    	 JOptionPane.showMessageDialog(new JPanel(), "Debe ingresar un nombre sin espacios ni caracteres especiales", "Error", JOptionPane.ERROR_MESSAGE);
		         return;
		     }
	}

	private ArrayList<String> toListString(DefaultComboBoxModel<String> cmod2) {
		ArrayList<String> ls = new ArrayList<String>();
		for(int i =0;i<cmod.getSize();i++){
			ls.add(cmod.getElementAt(i));
		}
		return ls;
	}
}
