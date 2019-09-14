package by.bntu.fitr.poisit.tarasenko.project.model.logic;

import by.bntu.fitr.poisit.tarasenko.project.controller.Controller;
import by.bntu.fitr.poisit.tarasenko.project.model.entity.Train;
import org.apache.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class TrainParser {
    private static final Logger LOG = Logger.getLogger(TrainParser.class);

    private static final String DEPARTURE_TITLE_TAG = "from";
    private static final String DEPARTURE_STATION_TAG = "title";
    private static final String TRANSPORT_TYPE_TAG = "transport_type";
    private static final String TRAIN_TEXT = "train";
    private static final String ARRIVAL_TIME_TAG = "arrival";
    private static final String DEPARTURE_TIME_TAG = "departure";
    private static final String DURATION_TAG = "duration";
    private static final String ARRIVE_TITLE_TAG = "to";
    private static final String ARRIVE_STATION_TAG = "title";
    private static final String THREAD_TAG = "thread";
    private static final String TRAIN_NUMBER_TAG = "number";
    private static final String EXEPT_DAYS_TAG = "days";
    private static final String ROUT_TAG = "title";
    private static final String FORMAT_TIME = "%02dч%02dм";


    public static ArrayList<Train> getTrainInformation(NodeList trainNodeList) {
        LOG.trace("GetTrainInformation method start");
        ArrayList<Train> trains = new ArrayList<>();
        for (int j = 0; j < trainNodeList.getLength(); j++) {
            NodeList segmentList = trainNodeList.item(j).getChildNodes();
            NodeList from = ((Element) segmentList).getElementsByTagName(DEPARTURE_TITLE_TAG);
            NodeList to = ((Element) segmentList).getElementsByTagName(ARRIVE_TITLE_TAG);
            NodeList thread = ((Element) segmentList).getElementsByTagName(THREAD_TAG);
            boolean isTrain = Parser.findTextByTag(from, TRANSPORT_TYPE_TAG).equals(TRAIN_TEXT);
            if (isTrain) {
                Train train = new Train();
                for (int i = 0; i < segmentList.getLength(); i++) {
                    Node node = segmentList.item(i);
                    String tag = node.getNodeName();
                    if (node.hasChildNodes()) {
                        String tagText = node.getFirstChild().getNodeValue();
                        switch (tag) {
                            case (DEPARTURE_TITLE_TAG): {
                                train.setDepartureStation(Parser.findTextByTag(from, DEPARTURE_STATION_TAG));
                            }
                            break;
                            case (ARRIVE_TITLE_TAG): {
                                train.setArrivalStation(Parser.findTextByTag(to, ARRIVE_STATION_TAG));
                            }
                            break;
                            case (ARRIVAL_TIME_TAG): {
                                train.setArrivalTime(formatDate(tagText));
                            }
                            break;
                            case (DEPARTURE_TIME_TAG):{
                                train.setDepartureTime(formatDate(tagText));
                            }break;
                            case (DURATION_TAG): {
                                train.setDuration(formatDuration(tagText));
                            }
                            break;
                            case (EXEPT_DAYS_TAG):{
                                train.setExceptDays(tagText);
                            }break;
                            case (THREAD_TAG):{
                                train.setNumber(Parser.findTextByTag(thread, TRAIN_NUMBER_TAG));
                                train.setRout(Parser.findTextByTag(thread, ROUT_TAG));
                            }break;
                        }
                    }
                }
                LOG.info("Information about train: "+train.toString());
                trains.add(train);
            }
        }
        return trains;
    }

    private static String formatDuration(String time){
        double timeD = Double.parseDouble(time);
        long hours = TimeUnit.SECONDS.toHours((long)timeD);
        long minutes = TimeUnit.SECONDS.toMinutes((long)timeD - TimeUnit.HOURS.toSeconds(hours));
        return String.format(FORMAT_TIME,hours, minutes);
    }

    private static String formatDate(String msg){
        String date = msg.substring(0,10);
        String time = msg.substring(11,16);
        return time + " " + date;
    }
}
