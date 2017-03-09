package modelo;

import java.io.Serializable;

public class Jugador implements Serializable {
	
	private String name;
	private int hability;
	private transient Descriptor descriptor;

	public Jugador(String nombre, int habilidad) {
		setName(nombre);
		setHability(habilidad);
		setDescriptor(new NormalDescriptor());
	}

	public int getHability() {
		return hability;
	}

	public void setHability(int hability) {
		this.hability = hability;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	 public String toString() {
		 if(getHability()==0)return "";
		 return getDescriptor().description(this);
	    }

	public void changeDescription() {
		getDescriptor().change(this);
		
	}

	public Descriptor getDescriptor() {
		if (descriptor ==null)this.setDescriptor( new NormalDescriptor());
			return descriptor;
	}

	public void setDescriptor(Descriptor descriptor) {
		this.descriptor = descriptor;
	}

}
