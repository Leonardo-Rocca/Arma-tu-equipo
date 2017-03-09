package web;

import java.util.ArrayList;
import java.util.HashMap;

import modelo.Jugador;
import persistencia.FileSystem;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class GenerateController {
	 
	private ViewController vc= ViewController.getViewController();

	public ModelAndView mostrar(Request request, Response response) {
			vc.initialize();
			return loadLists();
		  }

	private ModelAndView loadLists() {	
		    ArrayList<Jugador> jugadores = vc.getJugadoresParciales();		 //   return new ModelAndView(jugadores, "receta.hbs");
		    ArrayList<Jugador> jugadoresE1 = vc.getJugadoresE1();
		    ArrayList<Jugador> jugadoresE2 = vc.getJugadoresE2();
		    
		    HashMap<String, Object> viewModel = new HashMap<>();
		    viewModel.put("jugadores", jugadores);  
		    viewModel.put("jugadoresIzq",jugadoresE1);  	 
		    viewModel.put("jugadoresDer",jugadoresE2);  
		    
		    viewModel.put("hability1",vc.getHability1());  
		    viewModel.put("hability2",vc.getHability2()); 
		    return new ModelAndView(viewModel, "generar.hbs"); //recetas
	}
	
	public ModelAndView generarEquipos(Request request, Response response) {

			ViewController.getViewController().generarEquipos();
		
			return loadLists();
		  }
	
		public ModelAndView asignar(Request request, Response response) {
	//	  long id = Long.parseLong(request.params(":id")); ListadorDeRecetas.getListador().obtenerReceta(id);
			String n = request.params(":name");
			int h = Integer.parseInt(request.params(":team"));
			
			ViewController.getViewController().asigne(n,h);
			return loadLists();
	  }
		
/*
	 public ModelAndView listarRecetas(Request request, Response response) {
		 	Usuario usuarioLogueado = RepoUsuarios.getRepoUsuarios().get();
		 	List<Receta> recetas = new ArrayList<>();
		 	List<Receta> recetasTodas = ListadorDeRecetas.getListador().listarTodas();
		 	List<Receta> recetasConsultadas = RepoConsultas.getRepoConsultas().obtenerRecetasDeUsuario(usuarioLogueado);
		 	
		 	if (usuarioLogueado.getRecetasFavoritas().isEmpty()){
		 		if(recetasConsultadas.isEmpty()){
		 			recetas = RepoConsultas.getRepoConsultas().obtenerPrimerasRecetasMasConsultadas(recetasTodas,10);
		 		}
		 		else{
		 			recetas = recetasConsultadas;
		 		}
		 	}
		 	else{
		 		recetas = usuarioLogueado.getRecetasFavoritas();
		 	}
		    
		    HashMap<String, Object> viewModel = new HashMap<>();
		    viewModel.put("recetas", recetas);
		    
		    return new ModelAndView(viewModel, "recetas.hbs");
		  }*/
	 
}
