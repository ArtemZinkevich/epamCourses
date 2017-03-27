package com.rtmznk.port.dispatcher;

import com.rtmznk.port.entity.Ship;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


/**
 * Created by RTM on 21.03.2017.
 */
public class PortDispatcher {
    private static Logger logger = LogManager.getLogger(PortDispatcher.class);
    private ExecutorService executorService;
    private List<Future<Ship>> ships;

    public PortDispatcher() {
        executorService = Executors.newCachedThreadPool();
        ships = new ArrayList<>();
    }

    public void processShipQueue(Queue<Ship> shipQueue) {
        try {
            ships = executorService.invokeAll(shipQueue);
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, "Not all ship was serviced", shipQueue);

        }
    }

    public void processShip(Ship ship) {
        ships.add(executorService.submit(ship));
    }

    public void stopWorking() {
        executorService.shutdown();
    }

    public List<Ship> recieveFinishedShipList() {
        ArrayList<Ship> result = new ArrayList<>();
        for (Future<Ship> shipFuture : ships) {
            if (shipFuture.isDone()) {
                try {
                    result.add(shipFuture.get());
                } catch (InterruptedException | ExecutionException e) {
                    logger.log(Level.ERROR, "Every ship must be served by task.");
                }
            }
        }
        return result;
    }

}
