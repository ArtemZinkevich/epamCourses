package com.rmznk.xmltask.stax;

import com.rmznk.xmltask.entity.Flower;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class FlowerStAXBuilder {
    private static Logger logger = LogManager.getLogger(FlowerStAXBuilder.class);
    private StAXParser stAXParser;
    private InputStream inputStream;
    private ArrayList<Flower> flowers;

    public FlowerStAXBuilder() {
        stAXParser = new StAXParser();
    }

    public ArrayList<Flower> getFlowers() {
        return flowers;
    }

    public void buildListFlowers(String fileName) {
        try {
            inputStream = new FileInputStream(fileName);
            stAXParser.parse(inputStream);
        } catch (IOException e) {
            logger.log(Level.ERROR, "ошибка I/О потока: " + e);
        }
        flowers = stAXParser.getFlowers();
    }
}
