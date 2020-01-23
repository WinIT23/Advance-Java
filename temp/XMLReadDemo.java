import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;

class XMLRead {
    public void readXML() throws Exception {  
        File file = new File("config.xml");  
        
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();  
        DocumentBuilder db = dbf.newDocumentBuilder();  
        Document doc = db.parse(file);  
        
        doc.getDocumentElement().normalize();  
        System.out.println("Root element: " + doc.getDocumentElement().getNodeName());  
        NodeList nodeList = doc.getElementsByTagName("db_info");  
        
        for (int itr = 0; itr < nodeList.getLength(); itr++)   
        {  
            Node node = nodeList.item(itr);  
            System.out.println("\nNode Name :" + node.getNodeName());  
            if (node.getNodeType() == Node.ELEMENT_NODE)   
            {  
                Element eElement = (Element) node;  
                System.out.println("DB URL : " + eElement.getElementsByTagName("url").item(0).getTextContent());
                System.out.println("DB Name : " + eElement.getElementsByTagName("name").item(0).getTextContent());
                System.out.println("DB Pass : " + eElement.getElementsByTagName("passwd").item(0).getTextContent());
            }  
        }  
    } 
}

public class XMLReadDemo {
    public static void main(String[] args) {
        try {
            XMLRead obj = new XMLRead();
            obj.readXML();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}