package com.rtmznk.sphere.storage;

import com.rtmznk.sphere.entity.Sphere;
import com.rtmznk.sphere.math.action.SphereCalculator;
import com.rtmznk.sphere.math.result.FullMathResult;

import java.util.HashMap;

/**
 * Created by RTM on 17.02.2017.
 */
public class SingletonStorage {

    private HashMap<Sphere,FullMathResult> storage= new HashMap<>();
    private SingletonStorage() {
    }

    private static class SingletonHolder {
        private final static SingletonStorage INSTANCE = new SingletonStorage();
    }

    public static SingletonStorage getInstance() {
        return SingletonHolder.INSTANCE;
    }
    public void store(Sphere sphere){
        storage.put(sphere, SphereCalculator.getFullMathResult(sphere));
    }
}
