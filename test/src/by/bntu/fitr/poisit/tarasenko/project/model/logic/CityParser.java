package by.bntu.fitr.poisit.tarasenko.project.model.logic;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import java.util.HashMap;

public class CityParser {
    public static HashMap<String, String> getCityID(NodeList nodeList) {
        HashMap<String, String> cities = new HashMap<>();
        for (int j = 0; j < nodeList.getLength(); j++) {
            NodeList citiesList = nodeList.item(j).getChildNodes();
            for (int i = 0; i < citiesList.getLength(); i++) {
                if (citiesList.item(i) instanceof Element) {
                    Text text = (Text) citiesList.item(i).getFirstChild();
                    cities.put(text.getData().trim(),((Element) citiesList.item(i)).getAttribute("region"));
                }

            }
        }
        return cities;
    }

}
