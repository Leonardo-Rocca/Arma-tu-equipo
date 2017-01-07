package modelo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class main {

	static Jugador leo = new Jugador("Leo",7);
	static Jugador facu = new Jugador("Facu",9);
	static Jugador nico = new Jugador("Nico F",7);
	static Jugador cris = new Jugador("Cris",2);
	static Jugador edu = new Jugador("Edu",7);
	static Jugador juanjo = new Jugador("Juanjo",10);
	
	public static void main(String[] args) {
		ArmadorEquipos armador = new ArmadorEquipos();
		ArrayList<Jugador> jugadores = cargarJugadores3();
		ArrayList<Jugador> elTridente = cargarJugadoresTridente();
		ArrayList<Jugador> teamCris = cargarJugadoresTeamCris();
//	ArrayList<Match> m = armador.armarEquipo(jugadores); //m.get(0).imprimirEquipos();
		
	System.out.printf("El mas parejo \n");
	armador.armarEquipoMasParejo(jugadores).imprimirEquipos();
	
	System.out.printf("\nEl clásico \n");
	ArrayList<Match> m2 = armador.armarEquipoConJugadores(jugadores,new ArrayList<Jugador>(),new ArrayList<Jugador>());
	ArrayList<Match> m3 = armador.armarEquipoConJugadores(jugadores,elTridente);
	m2.get(0).imprimirEquipos();
	}


	private static ArrayList<Jugador> cargarJugadoresTeamCris() {
		ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
		jugadores.add(cris);
		jugadores.add(juanjo);
		jugadores.add(edu);
		return jugadores;
	}


	private static ArrayList<Jugador> cargarJugadoresTridente() {
		ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
		jugadores.add(facu);
		jugadores.add(nico);
		jugadores.add(leo);
		return jugadores;
	}

	private static ArrayList<Jugador> cargarJugadores() {
		ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
		jugadores.add(new Jugador("Juan Duran",1));
		jugadores.add(new Jugador("Eric",5));
		jugadores.add(new Jugador("Pan",6));
		jugadores.add(new Jugador("Juan DG",4));	
		jugadores.add(new Jugador("Viru",6));
		jugadores.add(new Jugador("Leo",7));
		jugadores.add(new Jugador("Cris",7));
		jugadores.add(new Jugador("Pablo",8));
		jugadores.add(new Jugador("Claudio",4));
		jugadores.add(new Jugador("Adro",2));
		return jugadores;
	}

	private static ArrayList<Jugador> cargarJugadores2() {
		ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
	jugadores.add(new Jugador("Juan",10));
	jugadores.add(new Jugador("Eric",5));
	jugadores.add(new Jugador("Marto",1));
	jugadores.add(new Jugador("J.malargue",9));	
	jugadores.add(new Jugador("a",3));
	jugadores.add(new Jugador("b",4));
	jugadores.add(new Jugador("c",2));
	jugadores.add(new Jugador("DG",6));
	jugadores.add(new Jugador("e",7));
	jugadores.add(new Jugador("f",8));
	return jugadores;
	}
	
	public static ArrayList<Jugador> cargarJugadores3() {
		ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
		jugadores.add(juanjo);
		jugadores.add(new Jugador("Eric",5));
		jugadores.add(cris);
		jugadores.add(facu);
		jugadores.add(leo);
		jugadores.add(nico);
		jugadores.add(edu);
		jugadores.add(new Jugador("Nico.A",8));
		jugadores.add(new Jugador("Titos",5));
		jugadores.add(new Jugador("Fede",3));
	return jugadores;
	}
	
	public static ArrayList<Jugador> cargarJugadoresFacu() {
		ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
		jugadores.add(new Jugador("Eric",4));
		jugadores.add(new Jugador("Marian",9));
		jugadores.add(new Jugador("Facu",8));
		jugadores.add(new Jugador("Leo",6));
		jugadores.add(new Jugador("gonza",6));
		jugadores.add(new Jugador("Cris",3));
		jugadores.add(new Jugador("Nico.F",7));
		jugadores.add(new Jugador("Titos",6));
		jugadores.add(new Jugador("Fede",5));
		jugadores.add(new Jugador("Fede C",2));
	return jugadores;
	}
	
}
