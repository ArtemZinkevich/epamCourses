package com.rtmznk.sphere.entity;


import com.rtmznk.sphere.observer.PointObserver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RTM on 16.02.2017.
 */
public class Point {
    private int x;
    private int y;
    private int z;
    private List<PointObserver> pointObservers = new ArrayList<>();

    public Point(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public void setX(int x) {
        this.x = x;
        notifyObservers();
    }

    public void setY(int y) {
        this.y = y;
        notifyObservers();
    }

    public void setZ(int z) {
        this.z = z;
        notifyObservers();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (x != point.x) return false;
        if (y != point.y) return false;
        return z == point.z;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        result = 31 * result + z;
        return result;
    }

    public void addObserver(PointObserver pointObserver) {
        pointObservers.add(pointObserver);
    }

    public void notifyObservers() {
        for (PointObserver observer : pointObservers) {
            observer.update();
        }
    }
}
