package com.rmznk.xmltask.sax;


import com.rmznk.xmltask.entity.Flower;
import com.rmznk.xmltask.entity.MultiplyingType;
import com.rmznk.xmltask.entity.PlantEnum;
import com.rmznk.xmltask.entity.SoilType;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

import java.util.ArrayList;


public class SaxContentHandler implements ContentHandler {

    private ArrayList<Flower> flowers = new ArrayList<Flower>();
    private Flower current = null;
    private PlantEnum currentEnum = null;

    ArrayList<Flower> getFlowers() {
        return flowers;
    }

    @Override
    public void setDocumentLocator(Locator locator) {

    }

    @Override
    public void startDocument() throws SAXException {

    }

    @Override
    public void endDocument() throws SAXException {

    }

    @Override
    public void startPrefixMapping(String prefix, String uri) throws SAXException {

    }

    @Override
    public void endPrefixMapping(String prefix) throws SAXException {

    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("flower")) {
            current = new Flower();
            current.setId(attributes.getValue(0));
            if (attributes.getLength() == 2) {
                current.setPrice(Integer.parseInt(attributes.getValue(1)));
            }
        } else if (qName.equals("growing_tips")) {
            current.getGrowingTips().setPhotophilous(Boolean.parseBoolean(attributes.getValue(0)));
        }
        if (!"flowers".equals(qName) && !"flower".equals(qName) && !"visual_parameters".equals(qName)
                && !"growing_tips".equals(qName)) {
            currentEnum = PlantEnum.valueOf(qName.toUpperCase());
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("flower")) {
            flowers.add(current);
        }
        currentEnum = null;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String value = new String(ch, start, length).trim();
        if (currentEnum == null) return;
        switch (currentEnum) {
            case NAME:
                current.setName(value);
                break;
            case SOIL:
                current.setSoil(SoilType.fromValue(value));
                break;
            case ORIGIN:
                current.setOrigin(value);
                break;
            case STALK_COLOR:
                current.getVisualParameters().setStalkColor(value);
                break;
            case LEAVES_COLOR:
                current.getVisualParameters().setLeavesColor(value);
                break;
            case AVERAGE_SIZE:
                current.getVisualParameters().setAverageSize(Integer.parseInt(value));
                break;
            case TEMPERATURE:
                current.getGrowingTips().setTemperature(Float.parseFloat(value));
                break;
            case WATERING:
                current.getGrowingTips().setWatering(Integer.parseInt(value));
                break;
            case MULTIPLYING:
                current.setMultiplying(MultiplyingType.fromValue(value));
                break;
        }
    }

    @Override
    public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {

    }

    @Override
    public void processingInstruction(String target, String data) throws SAXException {

    }

    @Override
    public void skippedEntity(String name) throws SAXException {

    }
}
