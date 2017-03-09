package web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

import javax.swing.filechooser.FileNameExtensionFilter;

import com.github.jknack.handlebars.internal.Files;

import modelo.Jugador;
import persistencia.FileSystem;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class PlayerController {
	private ViewController vc= ViewController.getViewController();
	
	  public ModelAndView mostrar(Request request, Response response) {
		  return new ModelAndView(null, "home.hbs");
	  }
		
		public ModelAndView listarJugadores(Request request, Response response) {		
			  HashMap<String, Object> viewModel = new HashMap<>();
			    viewModel.put("jugadores", 	new FileSystem().getListPlayers());  
			 return new ModelAndView(viewModel, "jugadores.hbs");
			  }
	
		public Void crear(Request request, Response response) {
			
			    String nombre = request.queryParams("nombre");	
				int habilidad = Integer.parseInt(request.queryParams("habilidad"));
				FileSystem.persistPlayer(new Jugador(nombre, habilidad));
			    response.redirect("/jugadores");
			    return null;
			  }

		public Void eliminar(Request request, Response response) {
		    String nombre = request.params(":name");	
		    
			ArrayList<Jugador> aux = new FileSystem().getListPlayers();
			aux = (ArrayList<Jugador>) aux.stream().filter(j ->  !j.getName().equals(nombre) ).collect(Collectors.toList());
		
			FileSystem.persistListPlayers(aux);
			response.redirect("/jugadores");
		    return null;
		  }
		
		
		//---no anda el archivo
		public ModelAndView editar(Request request, Response response) {
		    String nombre = request.params(":name");	
		    String habilidad = request.params(":hability");	
			  
		    HashMap<String, Object> viewModel = new HashMap<>();
		    viewModel.put("name",nombre);  
		    viewModel.put("hability",habilidad);  
		 return new ModelAndView(viewModel,"jugador.hbs");
		  }

		
		 
}