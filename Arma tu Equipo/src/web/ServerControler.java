package web;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerControler {
	int puerto = 90;
	Routes r = new Routes();
 
	final int ERROR = 0;
	final int WARNING = 1;
	final int DEBUG = 2;
 
     void depura(String mensaje) // los mensajes por defecto serán en modo depuracion</font>
	{
		depura(mensaje,DEBUG);
	} 
 
	// funcion para centralizar los mensajes de depuración
   void depura(String mensaje, int gravedad)
   {
        System.out.println("Mensaje: " + mensaje);
	} 
 
	// punto de entrada a nuestro programa
 public static void main(String [] array) 
	{
	 ServerControler instancia = new ServerControler(array); 
		instancia.arranca();
	}
 
	// constructor que interpreta los parameros pasados
 	public ServerControler(String[] param)
	{
		procesaParametros(); 
	}
 
	// parsearemos el fichero de entrada y estableceremos las variables de clase
 boolean procesaParametros()
	{
		return true; 
	}
 
  boolean arranca2()
	{
		depura("Arrancamos nuestro servidor",DEBUG);
		return true;
	}
  boolean arranca()
  {
	  depura("Arrancamos nuestro servidor",DEBUG);
	  
		try
		{
			ServerSocket s = new ServerSocket(90);
			depura("Quedamos a la espera de conexion");
	 
			while(true)  // bucle infinito .... ya veremos como hacerlo de otro modo
			{
				Socket entrante = s.accept();
			peticionWeb pCliente = new peticionWeb(entrante);
				pCliente.start();
			}
	 
		}
		catch(Exception e)
		{
			depura("Error en servidor\n" + e.toString());
		}
	 
		return true;
	}
}
