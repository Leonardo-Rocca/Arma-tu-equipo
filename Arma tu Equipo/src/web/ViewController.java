package web;

import java.util.ArrayList;
import java.util.stream.Collectors;

import modelo.ArmadorEquipos;
import modelo.Equipo;
import modelo.Jugador;
import modelo.Match;
import persistencia.FileSystem;

public class ViewController {

	private static ViewController controller = new ViewController();
	private ArrayList<Jugador> jugadoresPendientes;
	private ArrayList<Jugador> jugadoresE1;
	private ArrayList<Jugador> jugadoresE2;
	
	public static ViewController getViewController() {
		return controller ;
	}

	public ViewController() {
	 initialize();
	}

	public void initialize() {
		setJugadoresParciales(new ArrayList<Jugador>(new FileSystem().getListPlayers().subList(0, 10))); 
		 setJugadoresE1(new ArrayList<Jugador>());
		 setJugadoresE2(new ArrayList<Jugador>());
	}

	public ArrayList<Jugador> getJugadoresE1() {
		return jugadoresE1;
	}
	public void setJugadoresE1(ArrayList<Jugador> jugadoresE1) {
		this.jugadoresE1 = jugadoresE1;
	}
	public ArrayList<Jugador> getJugadoresE2() {
		return jugadoresE2;
	}

	public void setJugadoresE2(ArrayList<Jugador> jugadoresE2) {
		this.jugadoresE2 = jugadoresE2;
	}

	public void generarEquipos() {
		ArrayList<Match> m = new ArmadorEquipos().armarEquipoConJugadoresPendientes(getJugadoresParciales(), getJugadoresE1(), getJugadoresE2());
		Match match = m.get(0);

		setJugadoresE1(match.getJugadoresEquipo1());
		setJugadoresE2(match.getJugadoresEquipo2());
		setJugadoresParciales(new ArrayList<Jugador>());
	}

	public ArrayList<Jugador> getJugadoresParciales() {
		return jugadoresPendientes;
	}

	public void setJugadoresParciales(ArrayList<Jugador> jugadoresTotales) {
		this.jugadoresPendientes = jugadoresTotales;
	}

	public void asigne(String n, int i) {
		ArrayList<Jugador> listToAsigne =jugadoresPendientes;
		if(i==1)listToAsigne=jugadoresE1;
		if(i==2)listToAsigne=jugadoresE2;
		
		ArrayList<Jugador> aux = jugadoresPendientes;
		aux = (ArrayList<Jugador>) aux.stream().filter(j ->  j.getName().equals(n) ).collect(Collectors.toList());
	
		Jugador j = aux.get(0);
		listToAsigne.add(j);
		jugadoresPendientes.remove(j);
   //jugadoresTotales  = (ArrayList<Jugador>) jugadoresTotales.stream().filter(j -> !jugadoresE1.contains(j)).collect(Collectors.toList());

		return ;
	}

	public Object getHability1() {	
		return new Equipo(getJugadoresE1()).habilidad();
	}
	public Object getHability2() {	
		return new Equipo(getJugadoresE2()).habilidad();
	}


}
