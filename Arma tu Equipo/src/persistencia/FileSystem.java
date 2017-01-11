package persistencia;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.DefaultListModel;

import modelo.Jugador;

public class FileSystem {
	public static void main(String args[]) {
   //     persistPlayer();
        // Deserialización de objeto
        getPlayer();
    }

	public static void getPlayer() {
		try {
            Jugador object2;
            //abre el archivo
            FileInputStream fis = new FileInputStream("persisted-object.file");
            ObjectInputStream ois = new ObjectInputStream(fis);
            //lee el objeto del archivo
            object2 = (Jugador) ois.readObject();
            ois.close();
            //imprime los valores del objeto persistido
            System.out.println("object2: " + object2);   
        } catch (Exception e) {
            System.out.println("Exception during deserialization: " + e);
            System.exit(0);
        }
	}

	public static void persistPlayer(Jugador object1) {
		// Serialización de Objeto 
        try {
            //crea el objecto e imprime sus valores por consola
            System.out.println("object1: " + object1);
            //crea un fichero para persistir el objeto
            FileOutputStream fos = new FileOutputStream("persisted-object.file");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            //escribe el objeto serializado a un archivo
            oos.writeObject(object1);
            oos.flush();
            oos.close();
        } catch (Exception e) {
            System.out.println("Exception during serialization: " + e);
            System.exit(0);
        }
	}

    	public static void persistListPlayers(ArrayList<Jugador> object1) {
    		// Serialización de Objeto 
            try {
                //crea el objecto e imprime sus valores por consola
                System.out.println("object1: " + object1);
                //crea un fichero para persistir el objeto
                FileOutputStream fos = new FileOutputStream("persisted-object.file");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                //escribe el objeto serializado a un archivo
                oos.writeObject(object1);
                oos.flush();
                oos.close();
            } catch (Exception e) {
                System.out.println("Exception during serialization: " + e);
                System.exit(0);
            }
	}

		public ArrayList<Jugador> getListPlayers() {
            ArrayList<Jugador> object2 = new  ArrayList<Jugador>();
			try {

	            //abre el archivo
	            FileInputStream fis = new FileInputStream("persisted-object.file");
	            ObjectInputStream ois = new ObjectInputStream(fis);
	            //lee el objeto del archivo
	            object2 = (ArrayList<Jugador>) ois.readObject();
	            ois.close();
	            //imprime los valores del objeto persistido
	            System.out.println("object2: " + object2);   
	        } catch (Exception e) {
	            System.out.println("Exception during deserialization: " + e);
	            System.exit(0);
	        }
			return object2;
		}

		
		// -----------------DOMINIO VIEW------------------
		public void persistListPlayers(DefaultListModel<Jugador> lmod) {
			persistListPlayers(this.toListJugador(lmod));
			
		}    
		
		

		private ArrayList<Jugador> toListJugador(DefaultListModel<Jugador> jugadores) {
			ArrayList<Jugador> participantes = new ArrayList<Jugador>();
			for(int i = 0;i<jugadores.size();i++){
				participantes.add(jugadores.getElementAt(i));
			}
			return participantes;
		}

}
