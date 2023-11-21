package ej5;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;

import ej2.ListaPaises;
import ej2.Pais;

public class Ejercicio5 {

	public static void main(String[] args) throws IOException, SQLException {
		DAO gestor = new DAO();
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

		String fichero = "Paises.xml";
		FileInputStream fis = new FileInputStream(fichero);

		// Si la tabla ya esta creada descomentar gestor.start() y comentar gestor.crearTabla() antes de realizar
		// cualquier operacion CRUD
		gestor.crearTabla();
//		gestor.start();
		ListaPaises lp = (ListaPaises) xs.fromXML(fis);
		for (int i = 0; i < lp.getSize(); i++) {
			gestor.insertarPais(lp.getPais(i));
		}

		gestor.mostrarTabla();
		gestor.stop();

	}

}
