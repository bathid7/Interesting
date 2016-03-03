import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Parser {
	
	public static String currencyPairfromParser = null;
	public static String bidRatefromParser = null;
	public static String askRatefromParser = null;
	public static String highRatefromParser = null;
	public static String lowRatefromParser = null;
	public static String directionfromParser = null;
	public static String lastTimefromParser = null;
	public static double bidRatefromParserDouble = 0.0;
	public static double askRatefromParserDouble = 0.0;
	public static double highRatefromParserDouble = 0.0;
	public static double lowRatefromParserDouble = 0.0;
	public static int directionfromParserInt = 0;

	public static boolean parseXML(String currencyPair,double targetRate,int choice,String url)
	{
		try {
				boolean targetReached = false;
				
				NotifyUser userObj = new NotifyUser();
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				
				Document doc = dBuilder.parse(new URL(url).openStream());
				doc.getDocumentElement().normalize();
			 
				NodeList nList = doc.getElementsByTagName("Rate");
			 
				for (int temp = 0; temp < nList.getLength(); temp++) 
				{
					Node nNode = nList.item(temp);
			 
					if (nNode.getNodeType() == Node.ELEMENT_NODE) 
					{
			 
						Element eElement = (Element) nNode;
						currencyPairfromParser = eElement.getAttribute("Symbol");
						
						bidRatefromParser = eElement.getElementsByTagName("Bid").item(0).getTextContent();
						bidRatefromParserDouble = Double.parseDouble(bidRatefromParser);
						
						askRatefromParser = eElement.getElementsByTagName("Ask").item(0).getTextContent();
						askRatefromParserDouble = Double.parseDouble(askRatefromParser);
						
						highRatefromParser = eElement.getElementsByTagName("High").item(0).getTextContent();
						highRatefromParserDouble = Double.parseDouble(highRatefromParser);
						
						lowRatefromParser = eElement.getElementsByTagName("Low").item(0).getTextContent();
						lowRatefromParserDouble = Double.parseDouble(lowRatefromParser);
						
						directionfromParser = eElement.getElementsByTagName("Direction").item(0).getTextContent();
						directionfromParserInt = Integer.parseInt(directionfromParser);
						
						lastTimefromParser = eElement.getElementsByTagName("Last").item(0).getTextContent();
						targetReached = userObj.notificaton(currencyPair,currencyPairfromParser,targetRate,bidRatefromParserDouble,choice);
						
						if(targetReached)
							return targetReached;
					}
				}
		}
				
		catch (Exception e) {
			System.out.println("Error while parsing XML");
			System.out.println("Exception"+e);
			e.printStackTrace();
		}
		return false;
	}
}

