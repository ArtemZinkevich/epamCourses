package com.rtmznk.sphere.observer;


import com.rtmznk.sphere.entity.Point;

/**
 * Created by RTM on 17.02.2017.
 */
public interface PointObserver {
    void handleEvent(GeneralizedEvent<Point> event);
}

