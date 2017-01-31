package viewPackage;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class GenericForm {

	private GenericForm formAnterior;

	public GenericForm getFormAnterior() {
		return formAnterior;
	}

	public void setFormAnterior(GenericForm formAnterior) {
		this.formAnterior = formAnterior;
	}

	public Color getColor() {
		return new Color(0, 190, 0);
	}
	public Color getColorSecondary() {
		return new Color(32, 178, 170);
	
	}
	
	protected JLabel cargarFondo() {
		JLabel fondo = new JLabel("New label");
		fondo.setIcon(new ImageIcon("imagenes\\cesped.jpg"));
		fondo.setBounds(0, 0, 444, 272);
		return fondo;
	}
	
	protected JLabel cargarFondo2() {
		JLabel fondo = new JLabel("New label");
		fondo.setIcon(new ImageIcon("imagenes\\cesped3.jpg"));
		fondo.setBounds(0, 0, 454, 460);
		return fondo;
	}
}
