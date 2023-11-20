package ej1;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class Ejercicio1 {

	public static void main(String[] args) throws Throwable {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		DOMImplementation implementation = builder.getDOMImplementation();

		Document document = implementation.createDocument(null, "Paises", null);
		document.setXmlVersion("1.0");

		String[] paises = new String[] { "Belice", "El Salvador", "Guatemala", "Honduras", "Nicaragua", "Mexico",
				"Panama", "Costa Rica" };
		String[] presidentes = new String[] { "Froyla Tzalam", "Nayib Bukele", "Alejandro Giammattei", "Xiomara Castro",
				"Daniel Ortega", "Andre Manuel Lopez Obrador", "Laurentino Cortizo", "Rodrigo Chavez" };
		String[] pib = new String[] { "1.987", "74.818", "185.473", "85.625", "47.770", "2890.685", "128.500", "129.950" };
		String[] gini = new String[] { "53.3", "38.8", "48.3", "48.2", "46.2", "45.4", "50.9", "47.2" };
		
		
		
		for (int i = 0; i < paises.length; i++) {

			Element ele_pais = document.createElement("Pais");			
			document.getDocumentElement().appendChild(ele_pais);
			
			
			Element ele_nombre = document.createElement("Nombre");
			Text text_nombre = document.createTextNode(paises[i]);
			ele_pais.appendChild(ele_nombre);
			ele_nombre.appendChild(text_nombre);

			Element ele_presidentes = document.createElement("Presidente");
			Text text_presidentes = document.createTextNode(presidentes[i]);
			ele_pais.appendChild(ele_presidentes);
			ele_presidentes.appendChild(text_presidentes);
			
			Element ele_pib = document.createElement("PIB");
			Text text_pib = document.createTextNode(pib[i]);
			ele_pais.appendChild(ele_pib);
			ele_pib.appendChild(text_pib);
			
			Element ele_gini = document.createElement("Coeficiente_de_Gini");
			Text text_gini = document.createTextNode(gini[i]);
			ele_pais.appendChild(ele_gini);
			ele_gini.appendChild(text_gini);
		}

		DOMSource source = new DOMSource(document);
		Result result = new StreamResult(new File("Paises.xml"));
		Transformer transformer = TransformerFactory.newInstance().newTransformer();

		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty(OutputKeys.METHOD, "xml");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
		transformer.transform(source, result);

		Result console = new StreamResult(System.out);
		transformer.transform(source, console);
	}
}