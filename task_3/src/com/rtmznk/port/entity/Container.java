package com.rtmznk.port.entity;

import com.rtmznk.port.generator.ContainerIdGenerator;

/**
 * Created by RTM on 20.03.2017.
 */
public class Container {
    private int id;

    public Container() {
        id = ContainerIdGenerator.getNextId();
    }

    @Override
    public String toString() {
        return "Container{" + id + "}";
    }
}
