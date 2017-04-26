package com.rmznk.xmltask.dom;


import com.rmznk.xmltask.entity.Flower;
import com.rmznk.xmltask.factory.AbstractFlowerBuilder;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;

public class FlowerDOMBuilder implements AbstractFlowerBuilder {
    private static Logger logger = LogManager.getLogger(FlowerDOMBuilder.class);
    private ArrayList<Flower> flowers;
    private DocumentBuilderFactory documentBuilderFactory;
    private DOMParser parser;

    public FlowerDOMBuilder() {
        documentBuilderFactory = DocumentBuilderFactory.newInstance();
        parser = new DOMParser();
    }

    public ArrayList<Flower> getFlowers() {
        return flowers;
    }

    public void buildListFlowers(String fileName) {
            try {
                DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
                Document document = documentBuilder.parse(fileName);
                Element root = document.getDocumentElement();
                flowers = parser.parse(root);
            } catch (SAXException | ParserConfigurationException | IOException e) {
               logger.log(Level.ERROR,e);
            }
    }
}