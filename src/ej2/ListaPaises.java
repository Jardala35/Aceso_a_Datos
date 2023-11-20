package ej2;

import java.io.Serializable;
import java.util.ArrayList;

public class ListaPaises implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<Pais> listapaises = new ArrayList<>();

	public ListaPaises() {

	}

	public void setPais(Pais p) {
		this.listapaises.add(p);
	}

	public ArrayList<Pais> getListaPaises() {
		return this.listapaises;
	}

	public void printString() {
		for (int i = 0; i < listapaises.size(); i++) {
			System.out.println(listapaises.get(i).toString());

		}
	}

	public Pais getPais(int pos) {
		return this.listapaises.get(pos);
	}

	public int getSize() {
		return this.listapaises.size();
	}

}
