package modelo;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class ArmadorEquipos {
	
	
	
	
public	ArrayList<Match> armarEquipo(ArrayList<Jugador> jugadores){
	if(jugadores.size()==9){
		jugadores.add(new Jugador("", 0));
	}
	ArrayList<String> combS = CombinatoriaNumeros.devolverCombinatoriaString();
	ArrayList<Equipo> es1 = armarCombinacionesEquiposString(jugadores,combS);
	
	return armarMatchConRivales(es1,jugadores);
		
	}

//E------------------
private ArrayList<Equipo> armarCombinacionesEquiposString(ArrayList<Jugador> jugadores, ArrayList<String> comb) {
	int cantidadPlayers = comb.get(0).length();
	ArrayList<Equipo> es1 = new ArrayList<Equipo>();
	Equipo e1;
	int indiceJugador;
	for(int i = 0;i<comb.size();i++){
		e1 = new Equipo();
		for(int j = 0;j<cantidadPlayers;j++){
			indiceJugador =  Integer.parseInt(comb.get(i).substring(j, j+1) );
			e1.agregar(jugadores.get(indiceJugador));
		}
		es1.add(e1);
	}

	return es1;
}

private ArrayList<Match> armarMatchConRivales(ArrayList<Equipo> es1, ArrayList<Jugador> jugadores) {
	
	ArrayList<Match> matcheos= new ArrayList<Match>(); 
	es1.forEach(equipoArmado -> {
		matcheos.add(new Match(equipoArmado, this.rivalDe(equipoArmado,jugadores)));
					});
	return matcheos;
}

private Equipo rivalDe(Equipo equipoArmado, ArrayList<Jugador> jugadores) {
	ArrayList<Jugador> aux = jugadores;
	aux = (ArrayList<Jugador>) aux.stream().filter(j -> !equipoArmado.jugadores.contains(j)).collect(Collectors.toList());
	return new Equipo(aux);
}


public Match armarEquipoMasParejo(ArrayList<Jugador> jugadores) {

	   ArrayList<Match> m = this.armarEquipo(jugadores);
		 ordenarPorHabilidad(m);

		return m.get(0);
}

private void ordenarPorHabilidad(ArrayList<Match> m) {
	Comparator<Match> c = (s1, s2) -> s1.habilidadAcumulada().compareTo(s2.habilidadAcumulada());
	   m.sort(c);
}

public ArrayList<Match> armarEquipoConJugadores(ArrayList<Jugador> jugadores, ArrayList<Jugador> jugadoresEq1,	ArrayList<Jugador> jugadoresEq2) {
	
	ArrayList<Match> matcheos = this.armarEquipo(jugadores);
	matcheos = (ArrayList<Match>) matcheos.stream().filter(m -> m.equipo1.tiene(jugadoresEq1) &&  m.equipo2.tiene(jugadoresEq2)).collect(Collectors.toList());
	this.ordenarPorHabilidad(matcheos);
	return matcheos;
}

public ArrayList<Match> armarEquipoConJugadores(ArrayList<Jugador> jugadores, ArrayList<Jugador> jugadoresEquipo) {
	return this.armarEquipoConJugadores(jugadores, jugadoresEquipo,new ArrayList<Jugador>());
}	
	
	
	//version anterior
private ArrayList<Equipo> armarCombinacionesEquipos(ArrayList<Jugador> jugadores, ArrayList<Integer> comb) {

	int cantidadPlayers = Integer.toString(comb.get(0)).length();
	ArrayList<Equipo> es1 = new ArrayList<Equipo>();
	Equipo e1;
	int indiceJugador;
	for(int i = 0;i<comb.size();i++){
		e1 = new Equipo();
		for(int j = 0;j<cantidadPlayers;j++){
			indiceJugador =  Integer.parseInt(			Integer.toString(comb.get(i)).substring(j, j+1) );
			e1.agregar(jugadores.get(indiceJugador));
		}
		es1.add(e1);
	}
	
	return es1;
}


	
	
	/*
	// viejo
	 
	ArrayList<Match> armarEquipo(ArrayList<Jugador> jugadores){
	    
	      int total = jugadores.size();
	      int cantMax = 5;
	      
	      Equipo e1 = new Equipo();
	      Equipo e2 = new Equipo();
	      int elQueNoVa;
	      int i=0;
	      Match matcheo; 
	      ArrayList<Match> matcheos = new ArrayList<Match>();
	       
	     for(int j=0;j<cantMax;j++){
	          
	      elQueNoVa = j;
	      
	            for(i=0;i<cantMax;i++){
	   
	                if(i==elQueNoVa){
	                    e1.agregar(jugadores.get(total));
	                    e2.agregar(jugadores.get(elQueNoVa));
	                    }
	                else{
	                  e1.agregar(jugadores.get(i));
	                  e2.agregar(jugadores.get(i+cantMax));
	                   }
	              }
	        
	        
	         matcheo = new Match(e1,e2);
	        matcheos.add(matcheo);
	         e1 = new Equipo();
	         e2 = new Equipo();
	   }   
	  
	      return matcheos; 
	      
	}
*/
}
