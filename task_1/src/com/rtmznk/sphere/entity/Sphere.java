package com.rtmznk.sphere.entity;


import com.rtmznk.sphere.id.IdGenerator;
import com.rtmznk.sphere.observer.GeneralizedEvent;
import com.rtmznk.sphere.observer.PointObserver;
import com.rtmznk.sphere.observer.SphereObserver;

/**
 * Created by RTM on 16.02.2017.
 */
public class Sphere implements PointObserver {
    private final long id;
    private Point center;
    private int radius;
    private SphereObserver sphereObserver;


    public Sphere(Point center, int radius) {
        this.center = center;
        this.radius = radius;
        id = IdGenerator.getNextId();
        center.addObserver(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sphere sphere = (Sphere) o;

        if (radius != sphere.radius) return false;
        if (center != null ? !center.equals(sphere.center) : sphere.center != null) return false;
        return sphereObserver != null ? sphereObserver.equals(sphere.sphereObserver) : sphere.sphereObserver == null;
    }

    @Override
    public int hashCode() {
        int result = center != null ? center.hashCode() : 0;
        result = 31 * result + radius;
        result = 31 * result + (sphereObserver != null ? sphereObserver.hashCode() : 0);
        return result;
    }

    public SphereObserver getSphereObserver() {
        return sphereObserver;
    }

    public void setObserver(SphereObserver sphereObserver) {
        this.sphereObserver = sphereObserver;
    }

    public void removeObserver() {
        sphereObserver = null;
    }

    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        if (center.equals(this.center)) {
            this.center = center;
        } else {
            this.center = center;
            notifyObserver();
        }
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
        notifyObserver();
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "center(" + center.getX() +
                " , " + center.getY() +
                " , " + center.getZ() +
                " ) , radius=" + radius +
                '}';
    }

    @Override
    public void handleEvent(GeneralizedEvent<Point> event) {
        notifyObserver();
    }

    public void notifyObserver() {
        if (sphereObserver != null) {
            sphereObserver.handleEvent(new GeneralizedEvent<Sphere>(this));
        }
    }
}
