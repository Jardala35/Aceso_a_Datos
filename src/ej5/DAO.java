package ej5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import ej2.ListaPaises;
import ej2.Pais;

public class DAO {
	private Connection cable;

	public void start() {
		if (this.cable == null) {
			try {
				// cambiar la base de datos
				cable = DriverManager.getConnection("jdbc:sqlite:PracticaFinalACDSergioyPablodb");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void crearTabla() throws SQLException {
		start();
		String create = "CREATE TABLE Paises ( Pais VARCHAR(50) PRIMARY KEY , Presidente VARCHAR(50) NOT NULL, PIB VARCHAR(50) NOT NULL, Coeficiente_de_Gini REAL NOT NULL)";
		PreparedStatement st = cable.prepareStatement(create);
		st.execute();
		st.close();

	}

	public void insertarPais(Pais p) throws SQLException {
		if (!searchIfExists(p)) {
			String insert = "INSERT INTO Paises (Pais, Presidente, PIB, Coeficiente_de_Gini) VALUES (?, ?, ?, ?)";
			PreparedStatement st = cable.prepareStatement(insert);
			st.setString(1, p.getNombre());
			st.setString(2, p.getPresidente());
			st.setString(3, p.getPib());
			st.setDouble(4, p.getcGini());
			st.execute();
			st.close();
		}
	}

	public void borrarPais(Pais p) throws SQLException {
		String delete = "DELETE FROM Paises WHERE Pais=? AND Presidente=? AND PIB=? AND Coeficiente_de_Gini=?";
		PreparedStatement st = cable.prepareStatement(delete);
		st.setString(1, p.getNombre());
		st.setString(2, p.getPresidente());
		st.setString(3, p.getPib());
		st.setDouble(4, p.getcGini());
		st.execute();
		st.close();
	}

	public void consultarPais(Pais p) throws SQLException {
		String query = "SELECT * FROM Paises WHERE Pais=? AND Presidente=? AND PIB=? AND Coeficiente_de_Gini=?";
		PreparedStatement st = cable.prepareStatement(query);
		st.setString(1, p.getNombre());
		st.setString(2, p.getPresidente());
		st.setString(3, p.getPib());
		st.setDouble(4, p.getcGini());
		ResultSet rs = st.executeQuery();
		while (rs.next()) {

			System.out.println("Pais: " + rs.getString(0));
			System.out.println("Presidente: " + rs.getString(1));
			System.out.println("PIB: " + rs.getString(2));
			System.out.println("Coeficiente de Gini: " + rs.getString(3));

		}
		st.close();
	}

	public void modificarNombrePais(Pais p, String nombre) throws SQLException {
		if (!searchifPrimaryExists(nombre)) {
			String update = "UPDATE Paises SET Pais=? WHERE Pais=? AND Presidente=? AND PIB=? AND Coeficiente_de_Gini=?";
			PreparedStatement st = cable.prepareStatement(update);
			st.setString(1, nombre);
			st.setString(2, p.getNombre());
			st.setString(3, p.getPresidente());
			st.setString(4, p.getPib());
			st.setDouble(5, p.getcGini());
			st.executeUpdate();
			st.close();

		} else {
			System.out.println("Existe la clave primaria, use otro nombre de Pais");
		}

	}

	public void modificarPresidentePais(Pais p, String presidente) throws SQLException {
		String update = "UPDATE Paises SET Presidente=? WHERE Pais=?";
		PreparedStatement st = cable.prepareStatement(update);
		st.setString(1, presidente);
		st.setString(2, p.getNombre());

		st.executeUpdate();
		st.close();

	}

	public void modificarPIBPais(Pais p, String pib) throws SQLException {
		String update = "UPDATE Paises SET PIB=? WHERE Pais=?";
		PreparedStatement st = cable.prepareStatement(update);
		st.setString(1, pib);
		st.setString(2, p.getNombre());

		st.executeUpdate();
		st.close();

	}

	public void modificarGINIPais(Pais p, String cgini) throws SQLException {
		String update = "UPDATE Paises SET Coeficiente_de_Gini=? WHERE Pais=?";
		PreparedStatement st = cable.prepareStatement(update);
		st.setString(1, cgini);
		st.setString(2, p.getNombre());

		st.executeUpdate();
		st.close();

	}

	public void mostrarTabla() throws SQLException {
		PreparedStatement st = cable.prepareStatement("SELECT * FROM Paises");
		ResultSet res = st.executeQuery();
		ResultSetMetaData meta = st.getMetaData();
		System.out.println("##### Tabla: " + meta.getTableName(1) + " #####");
		while (res.next()) {
			System.out.println(meta.getColumnName(1) + ": " + res.getString(1) + " " + meta.getColumnName(2) + ": "
					+ res.getString(2) + " " + meta.getColumnName(3) + ": " + res.getString(3) + " "
					+ meta.getColumnName(4) + ": " + res.getString(4));

		}
	}

	public ListaPaises selectIntoLista() throws SQLException {
		String select = "SELECT * FROM Paises";
		ListaPaises aux = new ListaPaises();
		PreparedStatement st = cable.prepareStatement(select);
		ResultSet res = st.executeQuery();
		while (res.next()) {
			aux.setPais(new Pais(res.getString(1), res.getString(2), res.getString(3), res.getDouble(4)));
		}
		return aux;
	}

	private boolean searchIfExists(Pais p) {
		String query = "SELECT * FROM Paises WHERE  Pais=? AND Presidente=? AND PIB=? AND Coeficiente_de_Gini=?;";

		try {
			PreparedStatement st = cable.prepareStatement(query);
			st.setString(1, p.getNombre());
			st.setString(2, p.getPresidente());
			st.setString(3, p.getPib());
			st.setDouble(4, p.getcGini());
			ResultSet res = st.executeQuery();
			if (res.next()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	private boolean searchifPrimaryExists(String s) throws SQLException {
		String query = "SELECT * FROM Paises WHERE Pais=?;";
		PreparedStatement st = cable.prepareStatement(query);
		st.setString(1, s);
		ResultSet res = st.executeQuery();
		if (res.next()) {
			return true;
		} else {
			return false;
		}

	}

	public void stop() {
		if (this.cable == null) {

		} else {
			try {
				cable.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
