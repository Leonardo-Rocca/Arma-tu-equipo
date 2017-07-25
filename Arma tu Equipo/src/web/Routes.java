package web;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.*;

import spark.template.handlebars.HandlebarsTemplateEngine;

// ------ARMA TU EQUIPO-------------
public class Routes {

  public static void main(String[] args) {
    System.out.println("Iniciando servidor - arma tu equipo");

    HomeController home = new HomeController();
    PlayerController players = new PlayerController();
    HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
    GenerateController generar = new GenerateController();
    SelectionController seleccionar = new SelectionController();   
    port(8080);
    
    staticFileLocation("/public");

  //  get("/", generar::mostrar, engine);
    get("/", home::mostrar, engine);
    get("/index.html", (request, response) -> {
      response.redirect("/");
      return null;
    });
    get("/generar", generar::mostrar, engine);
    get("/generar/generate", generar::generarEquipos, engine);
    get("/generar/:name/:team", generar::asignar, engine);
    
    get("/jugadores",players::listarJugadores,engine);
    post("/jugadores", players::crear);
    get("/jugadores/eliminar/:name", players::eliminar);
    get("/jugador/:name/:hability", players::editar,engine);
    
	get("/seleccionar", seleccionar::mostrar, engine);
  /*  get("/login", login::loguear, engine);
    get("/consultaReceta",consulta::consultar,engine);
    get("/receta/:id", recetas::mostrar, engine);
    get("/recetas",recetas::listarRecetas,engine);
    get("/perfil",usuario::detallarUsuario,engine);
    post("/recetas", recetas::crear);
    get("/recetas/new", recetas::nuevo, engine);
   
*/
  }


}
