package ej4;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;

import ej2.ListaPaises;
import ej2.Pais;

public class Ejercicio4 {

	public static void main(String[] args) throws IOException, IOException {
		XStream xs = new XStream();
		xs.addPermission(NoTypePermission.NONE);
		xs.addPermission(NullPermission.NULL);
		xs.addPermission(PrimitiveTypePermission.PRIMITIVES);
		Class[] clases = { ListaPaises.class, Pais.class };
		xs.allowTypes(clases);

		xs.allowTypesByWildcard(new String[] { "xstream" });

		xs.alias("Paises", ListaPaises.class);
		xs.alias("Pais", Pais.class);

		xs.addImplicitCollection(ListaPaises.class, "listapaises");

		File file = new File("Paises.dat");
		ObjectOutputStream ous = new ObjectOutputStream(new FileOutputStream(file, false));
		ObjectInputStream liS = new ObjectInputStream(new FileInputStream(file));

		String fichero = "Paises.xml";
		FileInputStream fis = new FileInputStream(fichero);

		// Escribimos desde el archivo Paises.xml a Paises.dat
		ous.writeObject((ListaPaises) xs.fromXML(fis));

		try {
			// Leemos desde Paises.dat los objetos y los imprimimos por consola
			((ListaPaises) liS.readObject()).printString();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		ous.close();
		liS.close();

	}

}
