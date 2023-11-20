package ej2;

import java.io.Serializable;

public class Pais implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String Nombre;
	private String Presidente;
	private String PIB;
	private double Coeficiente_de_Gini;

	public Pais(String nombre, String presidente, String pib, double cGini) {
		this.Nombre = nombre;
		this.Presidente = presidente;
		this.PIB = pib;
		this.Coeficiente_de_Gini = cGini;
	}

	@Override
	public String toString() {
		return "Pais [nombre=" + Nombre + ", presidente=" + Presidente + ", pib=" + PIB + ", cGini="
				+ Coeficiente_de_Gini + "]";
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		this.Nombre = nombre;
	}

	public String getPresidente() {
		return Presidente;
	}

	public void setPresidente(String presidente) {
		this.Presidente = presidente;
	}

	public String getPib() {
		return PIB;
	}

	public void setPib(String pib) {
		this.PIB = pib;
	}

	public double getcGini() {
		return Coeficiente_de_Gini;
	}

	public void setcGini(double cGini) {
		this.Coeficiente_de_Gini = cGini;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
