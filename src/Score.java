import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class Score extends Extractor {
	private String localName;
	private String visitorName;
	private int localGoals;
	private int visitorGoals;

	public Score(){
		super();
	}

	public Score(String localName, String visitorName, int localGoals, int visitorGoals) {
		super();
		this.localName = localName;
		this.visitorName = visitorName;
		this.localGoals = localGoals;
		this.visitorGoals = visitorGoals;
	}

	public String getLocalName() {
		return localName;
	}

	public void setLocalName(String localName) {
		this.localName = localName;
	}

	public String getVisitorName() {
		return visitorName;
	}

	public void setVisitorName(String visitorName) {
		this.visitorName = visitorName;
	}

	public int getLocalGoals() {
		return localGoals;
	}

	public void setLocalGoals(int localGoals) {
		this.localGoals = localGoals;
	}

	public int getVisitorGoals() {
		return visitorGoals;
	}

	public void setVisitorGoals(int visitorGoals) {
		this.visitorGoals = visitorGoals;
	}

	@Override
	public String toString() {
		return "Score [localName=" + localName + ", visitorName=" + visitorName + ", localGoals=" + localGoals
				+ ", visitorGoals=" + visitorGoals + "]";
	}

	@Override
	public ArrayList<Score> extract(String xml1) {
		ArrayList<Score> list = new ArrayList<Score>();
		try {

			XPath xpath = XPathFactory.newInstance().newXPath();
			String exLocal = "/matchs/match/local/text()";
			String exVisitor = "/matchs/match/visitor/text()";
			String exScoreL = "/matchs/match/local_goals/text()";
			String exScoreV = "/matchs/match/visitor_goals/text()";



			InputSource is1 = new InputSource(new StringReader(xml1));
			InputSource is2 = new InputSource(new StringReader(xml1));
			InputSource is3 = new InputSource(new StringReader(xml1));
			InputSource is4 = new InputSource(new StringReader(xml1));

			NodeList listNames1 = (NodeList) xpath.evaluate(exLocal, is1, XPathConstants.NODESET);
			NodeList listNames2 = (NodeList) xpath.evaluate(exVisitor, is2, XPathConstants.NODESET);
			NodeList listNames3 = (NodeList) xpath.evaluate(exScoreL, is3, XPathConstants.NODESET);
			NodeList listNames4 = (NodeList) xpath.evaluate(exScoreV, is4, XPathConstants.NODESET);


			for(int i=0;i<listNames1.getLength();i++){
				Node n1 = listNames1.item(i);
				Node n2 = listNames2.item(i);
				Node n3 = listNames3.item(i);		
				Node n4 = listNames4.item(i);

				String value1 = n1.getNodeValue();
				//System.out.println(value1);

				String value2 = n2.getNodeValue();
				//System.out.println(value2);

				String value3 = n3.getNodeValue();
				//System.out.println(value3);

				String value4 = n4.getNodeValue();
				//System.out.println(value4);

				Score s = new Score();
				s.setLocalName(value1);
				s.setVisitorName(value2);
				if(value3.equals("x")){
					s.setLocalGoals(-1);
				}else{
					s.setLocalGoals(Integer.parseInt(value3));
				}
				if(value4.equals("x")){
					s.setVisitorGoals(-1);
				}else{
					s.setVisitorGoals(Integer.parseInt(value4));
				}
				list.add(s);
				//System.out.println(s);
			}

		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
