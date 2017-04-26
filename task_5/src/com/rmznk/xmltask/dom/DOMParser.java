package com.rmznk.xmltask.dom;

import com.rmznk.xmltask.entity.Flower;
import com.rmznk.xmltask.entity.MultiplyingType;
import com.rmznk.xmltask.entity.SoilType;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;

class DOMParser {
    ArrayList<Flower> parse(Element root) throws SAXException, IOException {
        ArrayList<Flower> plants = new ArrayList<Flower>();
        NodeList flowersNodes = root.getElementsByTagName("flower");
        Element flowerElement;
        Flower flower;
        for (int i = 0; i < flowersNodes.getLength(); i++) {
            flower = new Flower();
            flowerElement = (Element) flowersNodes.item(i);

            flower.setId(flowerElement.getAttribute("id"));
            flower.setName(getChildValue(flowerElement, "name"));
            flower.setSoil(SoilType.fromValue(getChildValue(flowerElement, "soil")));
            flower.setOrigin(getChildValue(flowerElement, "origin"));

            flower.getVisualParameters().setStalkColor(getChildValue(flowerElement, "stalk_color"));
            flower.getVisualParameters().setLeavesColor(getChildValue(flowerElement, "leaves_color"));
            flower.getVisualParameters().setAverageSize(
                    Integer.parseInt(getChildValue(flowerElement, "average_size")));

            flower.getGrowingTips().setTemperature(Float.parseFloat
                    (getChildValue(flowerElement, "temperature")));
            flower.getGrowingTips().setPhotophilous(Boolean.parseBoolean(
                    getChild(flowerElement, "growing_tips").getAttribute("photophilous")));
            flower.getGrowingTips().setWatering(Integer.parseInt(getChildValue(flowerElement, "watering")));

            flower.setMultiplying(MultiplyingType.fromValue(getChildValue(flowerElement, "multiplying")));
            plants.add(flower);
        }
        return plants;
    }

    private Element getChild(Element parent, String childName) {
        NodeList nodeList = parent.getElementsByTagName(childName);
        Element child = (Element) nodeList.item(0);
        return child;
    }

    private String getChildValue(Element parent, String childName) {
        Element child = getChild(parent, childName);
        Node node = child.getFirstChild();
        String value = node.getNodeValue();
        return value;
    }
}
