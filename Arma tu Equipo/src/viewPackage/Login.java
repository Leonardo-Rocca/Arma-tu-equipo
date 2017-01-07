package viewPackage;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JLabel;

import viewPackage.VistaPrincipal;
import modelo.*;

import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class Login {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
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
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Ingresar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			new	ViewArmadorEquipos().main(null);;
			}
		});
		btnNewButton.setBounds(136, 201, 288, 38);
		btnNewButton.setBorderPainted( false );
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Arma \r\ntu \r\nEquipo");
		lblNewLabel.setFont(new Font("SimSun", Font.BOLD, 20));
		lblNewLabel.setIcon(new ImageIcon("soccer.png"));
		lblNewLabel.setBounds(0, 0, 424, 256);
		frame.getContentPane().add(lblNewLabel);
	}
}
