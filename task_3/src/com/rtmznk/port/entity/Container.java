package com.rtmznk.port.entity;

import com.rtmznk.port.generator.ContainerIdGenerator;

/**
 * Created by RTM on 20.03.2017.
 */
public class Container {
    //TODO
    private final int ID;

    public Container() {
        ID = ContainerIdGenerator.getNextId();
    }

    @Override
    public String toString() {
        return "Container{" + ID + "}";
    }
}
