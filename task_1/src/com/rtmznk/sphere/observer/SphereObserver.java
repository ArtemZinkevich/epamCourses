package com.rtmznk.sphere.observer;

import com.rtmznk.sphere.entity.Sphere;

/**
 * Created by RTM on 17.02.2017.
 */
public interface SphereObserver {
    void handleEvent(GeneralizedEvent<Sphere> event);
}
