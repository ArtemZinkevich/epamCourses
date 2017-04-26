package com.rmznk.xmltask.stax;


import com.rmznk.xmltask.entity.Flower;
import com.rmznk.xmltask.entity.MultiplyingType;
import com.rmznk.xmltask.entity.PlantEnum;
import com.rmznk.xmltask.entity.SoilType;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;
import java.util.ArrayList;

class StAXParser {

    ArrayList<Flower> flowers = new ArrayList<Flower>();
    Flower current = null;
    PlantEnum currentEnum = null;

    ArrayList<Flower> getFlowers() {
        return flowers;
    }

    void parse(InputStream inputStream) {
        XMLInputFactory xmlInputFactory = XMLInputFactory.newFactory();
        try {
            XMLStreamReader xmlStreamReader = xmlInputFactory.createXMLStreamReader(inputStream);
            process(xmlStreamReader);
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

    private void process(XMLStreamReader xmlStreamReader) throws XMLStreamException {
        int type;
        String name;
        String value;
        while (xmlStreamReader.hasNext()) {
            type = xmlStreamReader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = xmlStreamReader.getLocalName();
                    if ("flower".equals(name)) {
                        current = new Flower();
                        current.setId(xmlStreamReader.getAttributeValue(0));
                    } else if (name.equals("growing_tips")) {
                        current.getGrowingTips().setPhotophilous(Boolean.parseBoolean(
                                xmlStreamReader.getAttributeValue(0)));
                    }
                    if (!"flowers".equals(name) && !"flower".equals(name)
                            && !"visual_parameters".equals(name) && !"growing_tips".equals(name)) {
                        currentEnum = PlantEnum.valueOf(name.toUpperCase());
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = xmlStreamReader.getLocalName();
                    if ("flower".equals(name)) {
                        flowers.add(current);
                    }
                    currentEnum = null;
                    break;
                case XMLStreamConstants.CHARACTERS:
                    value = xmlStreamReader.getText();
                    if (currentEnum == null) continue;
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
                        case WATERING:
                            current.getGrowingTips().setWatering(Integer.parseInt(value));
                            break;
                        case TEMPERATURE:
                            current.getGrowingTips().setTemperature(Float.parseFloat(value));
                            break;
                        case MULTIPLYING:
                            current.setMultiplying(MultiplyingType.fromValue(value));
                            break;
                    }
                    break;
            }
        }
    }
}

