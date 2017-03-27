package com.rtmznk.port.runner;

import com.rtmznk.port.creator.ShipCreator;
import com.rtmznk.port.dispatcher.PortDispatcher;
import com.rtmznk.port.storagetracker.PortStorageTracker;
import com.rtmznk.port.entity.Ship;
import com.rtmznk.port.parser.ShipStringParser;
import com.rtmznk.port.reader.TextFileReader;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

/**
 * Created by RTM on 21.03.2017.
 */
public class PortRunner {
    private final static String DEFAULT_INPUT_FILE_PATH = "data/input.txt";
    private static Logger logger = LogManager.getLogger(PortRunner.class);

    public static void main(String[] args) {
        TextFileReader fileReader = new TextFileReader();
        ShipStringParser parser = new ShipStringParser();
        ShipCreator shipCreator = new ShipCreator();
        List<String> stringFromDefaultFile = fileReader.readFileToStringList(DEFAULT_INPUT_FILE_PATH);
        List<int[]> shipParametrs = parser.recieveShipParams(stringFromDefaultFile);
        Queue<Ship> ships = shipCreator.createShipQueue(shipParametrs);
        System.out.println("ShipQueue size : " + ships.size());
        PortDispatcher dispatcher = new PortDispatcher();
        PortStorageTracker storageTracker = new PortStorageTracker();
        storageTracker.start();
        dispatcher.processShipQueue(ships);
        dispatcher.stopWorking();
        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            logger.log(Level.WARN, e);
        }
        List<Ship> finishedShips = dispatcher.recieveFinishedShipList();
        System.out.println("Finished ships : " + finishedShips);
    }
}
