package ej3;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;

import ej2.ListaPaises;
import ej2.Pais;

public class Ejercicio3 implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		XStream xs = new XStream();
		ListaPaises pais34 = new ListaPaises();

		xs.addPermission(NoTypePermission.NONE);
		xs.addPermission(NullPermission.NULL);
		xs.addPermission(PrimitiveTypePermission.PRIMITIVES);
		Class[] clases = { ListaPaises.class, Pais.class };
		xs.allowTypes(clases);

		xs.allowTypesByWildcard(new String[] { "xstream" });

		xs.alias("Paises", ListaPaises.class);
		xs.alias("Pais", Pais.class);

		xs.addImplicitCollection(ListaPaises.class, "listapaises");

		String fichero = "Paises.xml";

		FileInputStream fis = new FileInputStream(fichero);
		pais34 = (ListaPaises) xs.fromXML(fis);

		System.out.println("###");
		System.out.println("##Objetos del Archivo: " + fichero);
		pais34.printString();
		System.out.println("###");

		fis.close();

	}
}