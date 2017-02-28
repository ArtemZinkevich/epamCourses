package com.rtmznk.sphere.storage;

import com.rtmznk.sphere.entity.Sphere;
import com.rtmznk.sphere.math.action.SphereCalculator;
import com.rtmznk.sphere.math.result.FullMathResult;

import java.util.HashMap;

/**
 * Created by RTM on 17.02.2017.
 */
public class SingletonStorage {

    private static HashMap<Long, FullMathResult> storage = new HashMap<>();

    private SingletonStorage() {
    }

    public static HashMap<Long, FullMathResult> getStorage() {
        return storage;
    }

    public static SingletonStorage getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void store(Sphere sphere) {
        storage.put(sphere.getId(),
                SphereCalculator.getFullMathResult(sphere));
    }

    public void remove(Sphere sphere) {
        storage.remove(sphere.getId());
    }

    public FullMathResult getFullMathResult(Sphere sphere) {
        return storage.get(sphere.getId());
    }

    public void clear() {
        storage.clear();
    }

    public boolean contains(Sphere sphere) {
        return storage.containsKey(sphere.getId());
    }

    private static class SingletonHolder {
        private final static SingletonStorage INSTANCE = new SingletonStorage();
    }

}
