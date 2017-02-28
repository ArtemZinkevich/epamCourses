package com.rtmznk.sphere.observer;

import com.rtmznk.sphere.entity.Sphere;
import com.rtmznk.sphere.storage.SingletonStorage;

/**
 * Created by RTM on 17.02.2017.
 */
public class DimensionObserver implements SphereObserver {
    @Override
    public void handleEvent(GeneralizedEvent<Sphere> event) {
        SingletonStorage.getInstance().store(event.getSource());
    }
}
