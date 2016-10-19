import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class League extends Extractor{
	private int id;
	private String name;
	private int total_rounds;
	private int current_round;
	private int valor;
	private int valor2;
	
	public League(int id, String name, int total_rounds, int current_round) {
		super();
		this.id = id;
		this.name = name;
		this.total_rounds = total_rounds;
		this.current_round = current_round;
	}
	
	public League() {
		super();
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTotal_rounds() {
		return total_rounds;
	}
	public void setTotal_rounds(int total_rounds) {
		this.total_rounds = total_rounds;
	}
	public int getCurrent_round() {
		return current_round;
	}
	public void setCurrent_round(int current_round) {
		this.current_round = current_round;
	}

/*	@Override
	public ArrayList<League> extract(String xml) {
		return null;
		// TODO Auto-generated method stub
		
	}*/
	
	public ArrayList<League> extract(String xml) {
		ArrayList<League> list = new ArrayList<League>();
		try {

			XPath xpath = XPathFactory.newInstance().newXPath();
			String expression1 = "/leagues/league/id/text()";
			String expression2 = "/leagues/league/name/text()";
			String expression3 = "/leagues/league/current_round/text()";
			String expression4 = "/leagues/league/total_rounds/text()";
			InputSource is1 = new InputSource(new StringReader(xml));
			InputSource is2 = new InputSource(new StringReader(xml));
			InputSource is3 = new InputSource(new StringReader(xml));
			InputSource is4 = new InputSource(new StringReader(xml));
			NodeList listNames1 = (NodeList) xpath.evaluate(expression1, is1, XPathConstants.NODESET);
			NodeList listNames2 = (NodeList) xpath.evaluate(expression2, is2, XPathConstants.NODESET);
			NodeList listNames3 = (NodeList) xpath.evaluate(expression3, is3, XPathConstants.NODESET);
			NodeList listNames4 = (NodeList) xpath.evaluate(expression4, is4, XPathConstants.NODESET);


			for(int i=0;i<listNames1.getLength();i++){
				
				Node n1 = listNames1.item(i);
				Node n2 = listNames2.item(i);
				Node n3 = listNames3.item(i);		
				Node n4 = listNames4.item(i);

				String value1 = n1.getNodeValue();
				String value2 = n2.getNodeValue();
				String value3 = n3.getNodeValue();
				String value4 = n4.getNodeValue();


				League l = new League();
				l.setId(Integer.parseInt(value1));
				l.setName(value2);
				l.setCurrent_round(Integer.parseInt(value3));
				l.setTotal_rounds(Integer.parseInt(value4));
				list.add(l);
			}

		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public String toString(){
		return name;
	}
}
