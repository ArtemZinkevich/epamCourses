package com.rtmznk.sphere.creator;

import com.rtmznk.sphere.creator.exception.CreatingSphereException;
import com.rtmznk.sphere.entity.Point;
import com.rtmznk.sphere.entity.Sphere;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by RTM on 17.02.2017.
 */
public class SphereCreator {
    private static Logger logger = LogManager.getLogger(SphereCreator.class);

    public Sphere createSphere(int... args) throws CreatingSphereException {
        if (args.length==4) {
            int x = args[0];
            int y = args[1];
            int z = args[2];
            int radius = args[3];
            if(radius<0){
                throw new CreatingSphereException("Logically incorrect radius :" + radius);
            }
            Sphere sphere = new Sphere(new Point(x, y, z), radius);
            logger.info("New sphere was created: " + sphere);
            return sphere;
        } else {
            throw new CreatingSphereException("incorrect arguments amount(must be 4): " + Arrays.asList(args));
        }
    }

    public List<Sphere> createSphereList(List<int[]> args) {
        List<Sphere> spheres = new ArrayList<>();
        for (int[] list : args) {
            try {
                spheres.add(createSphere(list));
            } catch (CreatingSphereException e) {
                logger.error(e);
            }
        }
        return spheres;
    }

}
