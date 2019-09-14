package by.bntu.fitr.poisit.tarasenko.project.model.logic;

import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class CityParserTest {
    public static final String CITIES_ID_PATH =
            "d:\\HomeWork\\Java\\JavaFX\\src\\by\\bntu\\fitr\\poisit\\tarasenko\\project\\util\\cities.xml";

    HashMap<String, String> citiesID = new HashMap<>(CityParser.getCityID(Parser.doNodeList(CITIES_ID_PATH)));

    CityParserTest() throws IOException, SAXException, ParserConfigurationException {
    }

    @Test
    void getCityIDtestCheckForNull() {
        assertNotEquals(null, citiesID);
    }

    @Test
    void getCityIDtestMinsk() {
        String expected = "157";
        String result = citiesID.get("Минск");
        assertEquals(expected, result);
    }

    @Test
    void getCityIDtestVitebsk() {
        String expected = "154";
        String result = citiesID.get("Витебск");
        assertEquals(expected, result);
    }

    @Test
    void getCityIDtestMolodechno() {
        String expected = "23254";
        String result = citiesID.get("Молодечно");
        assertEquals(expected, result);
    }

    @Test
    void getCityIDtestLida() {
        String expected = "21144";
        String result = citiesID.get("Лида");
        assertEquals(expected, result);
    }
}