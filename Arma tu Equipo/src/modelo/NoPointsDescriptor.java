package modelo;

public class NoPointsDescriptor extends Descriptor {

	public void change(Jugador jugador) {
		jugador.setDescriptor(new NormalDescriptor());
	}

	public String description(Jugador jugador) {
		      return jugador.getName();	
	}

}