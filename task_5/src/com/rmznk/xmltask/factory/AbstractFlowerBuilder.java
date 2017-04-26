package com.rmznk.xmltask.factory;

import com.rmznk.xmltask.entity.Flower;

import java.util.ArrayList;

/**
 * Created by RTM on 27.04.2017.
 */
public interface AbstractFlowerBuilder {
    void buildListFlowers(String fileName);

    ArrayList<Flower> getFlowers();
}
