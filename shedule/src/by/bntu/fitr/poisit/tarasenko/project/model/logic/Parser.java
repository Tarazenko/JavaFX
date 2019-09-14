package by.bntu.fitr.poisit.tarasenko.project.model.logic;

import org.w3c.dom.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Parser {

    public static NodeList doNodeList(String path) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File(path));
        //Document document = builder.parse(new File("./cities.xml"));
        Element element = document.getDocumentElement();
        return element.getChildNodes();
    }

    public static String getNodeText(Node node) {
        Text text = (Text) node.getFirstChild();
        return text.getData().trim();
    }

    public static String findTextByTag(NodeList nodeList, String tag) {
        String text = " ";
        nodeList = nodeList.item(0).getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            if (nodeList.item(i).getNodeName().equals(tag)) {
                text = getNodeText(nodeList.item(i));
            }
        }
        return text;
    }

    public static Document doDocumentFromUrlRequest(String request)
            throws IOException, ParserConfigurationException, SAXException {
        URL url = new URL(request);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        return db.parse(new InputSource(url.openStream()));
    }

    public static NodeList doNodeListByTag(Document document, String tag){
        return document.getDocumentElement().getElementsByTagName(tag);
    }
}