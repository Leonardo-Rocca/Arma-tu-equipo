package web;

import java.util.HashMap;

import persistencia.FileSystem;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class SelectionController {

	public ModelAndView mostrar(Request request, Response response) {
		  HashMap<String, Object> viewModel = new HashMap<>();
		    viewModel.put("jugadores", 	new FileSystem().getListPlayers());  
			//return new ModelAndView(viewModel, "seleccionar.hbs");
			return new ModelAndView(viewModel, "generar-drag.hbs");
		  }
}
