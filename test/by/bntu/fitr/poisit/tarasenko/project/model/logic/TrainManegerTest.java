package by.bntu.fitr.poisit.tarasenko.project.model.logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrainManegerTest {

    @Test
    void getInformationAboutTrainsMinskVitebsk() {
        assertNotEquals(null,
                TrainManeger.getInformationAboutTrains("157", "154", "20-05-2019"));
    }
    @Test
    void getInformationAboutTrainsVitebskMinsk() {
        assertNotEquals(null,
                TrainManeger.getInformationAboutTrains("154", "157", "20-05-2019"));
    }
    @Test
    void getInformationAboutTrainsMinskGrodno() {
        assertNotEquals(null,
                TrainManeger.getInformationAboutTrains("157", "10274", "20-05-2019"));
    }
    @Test
    void getInformationAboutTrainsMinskPolock() {
        assertNotEquals(null,
                TrainManeger.getInformationAboutTrains("157", "10275", "20-05-2019"));
    }
    @Test
    void getInformationAboutTrainsVitebskGrodno() {
        assertNotEquals(null,
                TrainManeger.getInformationAboutTrains("154", "10274", "20-05-2019"));
    }
}