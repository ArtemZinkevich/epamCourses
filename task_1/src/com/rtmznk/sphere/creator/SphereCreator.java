package com.rtmznk.sphere.creator;

import com.rtmznk.sphere.entity.Point;
import com.rtmznk.sphere.entity.Sphere;
import com.rtmznk.sphere.exception.InvalidArgumentsException;
import com.rtmznk.sphere.observer.DimensionObserver;
import com.rtmznk.sphere.validator.ArguementsValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by RTM on 17.02.2017.
 */
public class SphereCreator {
    private static final DimensionObserver ALL_SPHERE_OBSERVER = new DimensionObserver();
    private static Logger logger = LogManager.getLogger(SphereCreator.class);

    public Sphere createSphere(int... args) throws InvalidArgumentsException {
        if (ArguementsValidator.isArgumentsValid(args)) {
            int x = args[0];
            int y = args[1];
            int z = args[2];
            int radius = args[3];
            Sphere sphere = new Sphere(new Point(x, y, z), radius);
            sphere.setObserver(ALL_SPHERE_OBSERVER);
            sphere.update();
            logger.info("New sphere was created: " + sphere);
            return sphere;
        }
        try {
            throw new InvalidArgumentsException("Arguments are logically invalid");
        } catch (InvalidArgumentsException e) {
            logger.error(e);
            throw e;
        }
    }
    public List<Sphere> createSpheres(List<int[]> args) throws InvalidArgumentsException {
        List<Sphere> spheres= new ArrayList<>();
        for(int[] list:args){
           spheres.add(createSphere(list));
        }
        return spheres;
    }

}
