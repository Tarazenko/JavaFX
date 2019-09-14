package by.bntu.fitr.poisit.tarasenko.project.controller;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.ResourceBundle;

import by.bntu.fitr.poisit.tarasenko.project.model.entity.Train;
import by.bntu.fitr.poisit.tarasenko.project.model.logic.CityParser;
import by.bntu.fitr.poisit.tarasenko.project.model.logic.Parser;
import by.bntu.fitr.poisit.tarasenko.project.model.logic.TrainManeger;
import by.bntu.fitr.poisit.tarasenko.project.model.logic.TrainParser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import org.apache.log4j.Logger;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;

public class Controller {
    private static final Logger LOG = Logger.getLogger(Controller.class);
    public static final String ERROR_CITES_ID = "Ошибка, не найден 'cities.xml'. Попробуйте перезапустить приложение.";
    public static final String API_KEY = "fbe0b71f-bba7-4f35-b8ed-7ba553d139b6";
    public static final String CITIES_ID_PATH =
            //"d:\\HomeWork\\Java\\JavaFX\\src\\by\\bntu\\fitr\\poisit\\tarasenko\\project\\util\\cities.xml";
            "./src/by/bntu/fitr/poisit/tarasenko/project/util/cities.xml";
            //"./cities.xml";
    public static final String TRAIN_INFO_TAG = "segment";
    public static final String DATE_FORMAT = "dd-MM-yyyy";
    public static final String ERROR_STATION_MASSAGE = "Поезда в указанном направлении не найдены.";
    public static final String NO_DEPARTURE_MESSAGE = "Станция отправления не найдена.";
    public static final String NO_ARRIVAL_MESSAGE = "Станция прибытия не найдена.";
    public static final String NO_DATA_MESSAGE = "Проверьте введенные данные.";

    private ObservableList<Train> trains = FXCollections.observableArrayList();
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField fromEdit;

    @FXML
    private TextField toEdit;

    @FXML
    private Button findButton;

    @FXML
    private TableView<Train> trainTable;

    @FXML
    private TableColumn<Train, String> numberColumn;

    @FXML
    private TableColumn<Train, String> routColumn;

    @FXML
    private TableColumn<Train, String> arriveColumn;

    @FXML
    private TableColumn<Train, String> departureColumn;

    @FXML
    private TableColumn<Train, String> durationColumn;

    @FXML
    private DatePicker dataField;

    @FXML
    private ImageView reverceImage;

    @FXML
    void initialize() {
        //set a default date
        trainTable.setPlaceholder(new Label(""));
        dataField.setValue(NOW_LOCAL_DATE());
        LOG.trace("Set default date: " + NOW_LOCAL_DATE());
        //get map with cities ID
        boolean success = true;
        HashMap<String, String> citiesID = null;
        try {
            citiesID = new HashMap<>(CityParser.getCityID(Parser.doNodeList(CITIES_ID_PATH)));
            LOG.info("Cities recived");
        } catch (ParserConfigurationException | SAXException | IOException e) {
            success = false;
            LOG.error("Problems with cities id:" + e.toString());
        }
        LOG.info("Success parse of cities: " + success);
        HashMap<String, String> finalCitiesID = citiesID;
        boolean finalSuccess = success;
        //press button
        findButton.setOnAction(event -> {
            LOG.trace("Press button \"Find\"");
            if (finalSuccess) {
                String departureID = finalCitiesID.get(fromEdit.getText().trim());
                LOG.trace("Departure station:" + departureID);
                String arrivalID = finalCitiesID.get(toEdit.getText().trim());
                LOG.trace("Arrival station:" + arrivalID);
                String date = dataField.getValue().toString();
                LOG.trace("Date: " + dataField.getValue().toString());
                boolean isNull = checkDepartureAndArrival(departureID, arrivalID);
                if (!isNull) {
                    ArrayList<Train> trainInformation =
                            TrainManeger.getInformationAboutTrains(departureID, arrivalID, date);
                    if (trainInformation.size() != 0) {
                        ObservableList<Train> trains = FXCollections.observableArrayList(trainInformation);
                        fillTable(trains);
                    } else
                    {
                        LOG.info("User input wrong city");
                        setTextInTable(ERROR_STATION_MASSAGE);
                    }
                }
            } else {
                LOG.warn("Write user about problems with cities id");
                setTextInTable(ERROR_CITES_ID);
            }
        });
        reverceImage.setOnMouseClicked(event ->

        {
            LOG.trace("User press reverce image");
            reverceDirection();
        });
    }


    private static final LocalDate NOW_LOCAL_DATE() {
        String date = new SimpleDateFormat(DATE_FORMAT).format(Calendar.getInstance().getTime());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        return LocalDate.parse(date, formatter);
    }

    private void fillTable(ObservableList<Train> trains) {
        LOG.trace("Fill table");
        numberColumn.setCellValueFactory(new PropertyValueFactory<Train, String>("number"));
        routColumn.setCellValueFactory(new PropertyValueFactory<Train, String>("rout"));
        departureColumn.setCellValueFactory(new PropertyValueFactory<Train, String>("departureTime"));
        arriveColumn.setCellValueFactory(new PropertyValueFactory<Train, String>("arrivalTime"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<Train, String>("duration"));
        trainTable.setItems(trains);
    }

    private void reverceDirection() {
        String saveFrom = fromEdit.getText();
        fromEdit.setText(toEdit.getText());
        toEdit.setText(saveFrom);
        LOG.trace("Reverce direction");
    }

    private void setTextInTable(String text) {
        LOG.trace("Set text in table: " + text);
        trainTable.getItems().clear();
        trainTable.setPlaceholder(new Label(text));
    }

    boolean checkDepartureAndArrival(String departure, String arrival) {
        boolean ans = false;
        if (departure == null && arrival == null) {
            ans = true;
            LOG.info("User don't input departure station and arrival station");
            setTextInTable(NO_DATA_MESSAGE);
        } else if (departure == null) {
            ans = true;
            LOG.info("User don't input departure station");
            setTextInTable(NO_DEPARTURE_MESSAGE);
        } else if (arrival == null) {
            ans = true;
            LOG.info("User don't input arrival station");
            setTextInTable(NO_ARRIVAL_MESSAGE);
        }
        return ans;
    }
}
