package com.rmznk.xmltask.sax;


import com.rmznk.xmltask.entity.Flower;
import com.rmznk.xmltask.factory.AbstractFlowerBuilder;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.ArrayList;

public class FlowersSAXBuilder implements AbstractFlowerBuilder {
    private static Logger logger = LogManager.getLogger(FlowersSAXBuilder.class);
    private ArrayList<Flower> flowers;
    private SaxContentHandler handler;
    private XMLReader reader;

    public FlowersSAXBuilder() {

        handler = new SaxContentHandler();
        try {
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(handler);
        } catch (SAXException e) {
            logger.log(Level.ERROR, "ошибка SAX парсера: " + e);
        }
    }

    public ArrayList<Flower> getFlowers() {
        return flowers;
    }

    public void buildListFlowers(String fileName) {
        try {
            reader.parse(fileName);
        } catch (SAXException e) {
            logger.log(Level.ERROR, "ошибка SAX парсера: " + e);
        } catch (IOException e) {
            logger.log(Level.ERROR, "ошибка I/О потока: " + e);
        }
        flowers = handler.getFlowers();
    }
}
