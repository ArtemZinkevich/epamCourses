package com.rtmznk.port.creator;

import com.rtmznk.port.entity.Container;
import com.rtmznk.port.entity.Ship;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by RTM on 22.03.2017.
 */
public class ShipCreator {
    private static Logger logger = LogManager.getLogger(ShipCreator.class);

    public Queue<Ship> createShipQueue(List<int[]> params) {
        Queue<Ship> shipQueue = new LinkedList<>();
        for (int[] paramArray : params) {
            Ship nextShip;
            List<Container> shipContainers;
            if (paramArray.length == 3) {
                shipContainers = ContainerCreator.createContainerList(paramArray[1]);
                nextShip = new Ship(paramArray[0], shipContainers, paramArray[2]);
                shipQueue.add(nextShip);
            } else {
                logger.log(Level.WARN, "Incorrect parametrs.Will be ignored.");
            }
        }
        return shipQueue;
    }
}
