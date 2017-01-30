package viewPackage;

import java.awt.Color;

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
}
