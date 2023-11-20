package ej6;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;

import ej2.ListaPaises;
import ej2.Pais;
import ej5.DAO;

public class Ejercicio6 {
	public static void main(String[] args) throws FileNotFoundException, SQLException {
		DAO gestor = new DAO();

		gestor.start();
		// + 10 millones al PIB
		ListaPaises lista = gestor.selectIntoLista();
		System.out.println("Datos por defecto");
		gestor.mostrarTabla();
		for (int i = 0; i < lista.getSize(); i++) {
			BigDecimal aux = new BigDecimal(Double.parseDouble(lista.getPais(i).getPib()) + 0.010).setScale(3,
					RoundingMode.HALF_UP);
			gestor.modificarPIBPais(lista.getPais(i), aux.toString());
		}
		System.out.println("Modificacion del PIB");
		gestor.mostrarTabla();
		// modificacion del Coeficiente de Gini
		lista = gestor.selectIntoLista();
		for (Pais pais : lista.getListaPaises()) {
			if (pais.getNombre().equals("El Salvador") || pais.getNombre().equals("Honduras")
					|| pais.getNombre().equals("Mexico")) {
				BigDecimal aux = new BigDecimal((pais.getcGini() * 2) / 3.0).setScale(2, RoundingMode.HALF_UP);
				gestor.modificarGINIPais(pais, aux.toString());

			}
		}
		System.out.println("modificacion del coeficiente de gini");
		gestor.mostrarTabla();

	}
}
