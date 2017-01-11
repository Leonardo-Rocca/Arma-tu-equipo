package persistencia;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import modelo.Jugador;

public class PersistidorGrups {
	public void inicializar(){
		this.persistFile("persisted-object.file");
	}
	
	
	//file -> apunta al archivo a leer
	
	public static String getFile() {
		try {
            FileInputStream fis = new FileInputStream("persisted-root.file");
            ObjectInputStream ois = new ObjectInputStream(fis);
            String file =  (String) ois.readObject();
            ois.close();
            return file;
        } catch (Exception e) {
            System.out.println("Exception during deserialization: " + e);
            System.exit(0);
        }
		return null;
	}

	public static void persistFile(String f) {
        try {
            FileOutputStream fos = new FileOutputStream("persisted-root.file");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            //escribe el objeto serializado a un archivo
            oos.writeObject(f);
            oos.flush();
            oos.close();
        } catch (Exception e) {
            System.out.println("Exception during serialization: " + e);
            System.exit(0);
        }
	}
	public static ArrayList<String> getGroups() {
		ArrayList<String> sg = new ArrayList<String>();
		sg.add("persisted-object.file");
		try {
            FileInputStream fis = new FileInputStream(getFile());
            ObjectInputStream ois = new ObjectInputStream(fis);
             sg =  (ArrayList<String>) ois.readObject();
            ois.close();
        } catch (Exception e) {
            System.out.println("Exception during deserialization: " + e);
            System.exit(0);
        }
		return sg;		
	}
	public static void persistGroups(ArrayList<String> groups) {
        try {
            FileOutputStream fos = new FileOutputStream(getFile());
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(groups);
            oos.flush(); oos.close();
        } catch (Exception e) {
            System.out.println("Exception during serialization: " + e);
            System.exit(0);
        }
	}
}
