package by.bntu.fitr.poisit.tarasenko.project.model.logic;

import by.bntu.fitr.poisit.tarasenko.project.controller.Controller;
import by.bntu.fitr.poisit.tarasenko.project.model.entity.Train;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.log4j.Logger;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TrainManeger {
    private static final Logger LOG = Logger.getLogger(TrainManeger.class);

    public static final String API_KEY = "fbe0b71f-bba7-4f35-b8ed-7ba553d139b6";
    public static final String TRAIN_INFO_TAG = "segment";

    public static ArrayList<Train> getInformationAboutTrains(String departureID, String arrivalID, String date) {
        ArrayList<Train> informationArray = new ArrayList<>();
        String request = doRequestString(departureID, arrivalID, date);
        NodeList requestInfo = getInfoFromRequest(request);
        //get list information about trains
        if (requestInfo != null) {
            informationArray = TrainParser.getTrainInformation(requestInfo);
            LOG.info("Information array:" + informationArray.toString());
        }
        return informationArray;
    }

    private static String doRequestString(String from, String to, String date) {
        LOG.trace("URL: " + "https://api.rasp.yandex.net/v3.0/search/" +
                "?apikey=" + API_KEY + "&format=xml&from=c" + from + "&to=c" + to + "&date=" + date);
        return "https://api.rasp.yandex.net/v3.0/search/" +
                "?apikey=" + API_KEY + "&format=xml&from=c" + from + "&to=c" + to + "&date=" + date;
    }

    private static NodeList getInfoFromRequest(String request) {
        NodeList information = null;
        try {
            information = Parser.doNodeListByTag(Parser.doDocumentFromUrlRequest(request), TRAIN_INFO_TAG);
        } catch (IOException | ParserConfigurationException | SAXException e) {
            LOG.warn("Don't recive anything from URL request.");
        }
        return information;
    }

}
