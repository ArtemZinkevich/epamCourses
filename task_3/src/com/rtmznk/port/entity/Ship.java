package com.rtmznk.port.entity;

import com.rtmznk.port.exception.PortResourceException;
import com.rtmznk.port.generator.ShipIdGenerator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Created by RTM on 20.03.2017.
 */
public class Ship implements Callable<Ship> {
    private static Logger logger = LogManager.getLogger(Ship.class);
    private final int ID;
    private final int maxContainersAmount;
    private List<Container> containers;
    private int containersToLoadAmount;
    private Port port;

    public Ship(int maxContainersAmount, List<Container> containers, int containersToLoadAmount) {
        this.ID = ShipIdGenerator.getNextId();
        this.maxContainersAmount = maxContainersAmount;
        if (maxContainersAmount < 0) {
            maxContainersAmount = 0;
            logger.log(Level.WARN, "MaxContainersAmount below zero. Set equal to 0.");
        }
        this.containers = containers;
        this.containersToLoadAmount = containersToLoadAmount;
        if (containersToLoadAmount < 0) {
            containersToLoadAmount = 0;
            logger.log(Level.WARN, "ContainersToLoadAmount below zero. Set equal to 0.");
        }
        if (containersToLoadAmount > maxContainersAmount) {
            containersToLoadAmount = maxContainersAmount;
            logger.log(Level.WARN, "Trying to create ship with containersToLoad amount more" +
                    " than max containers amount.\nContainers to load amount is set equal to MaxContainersAmount.");
        }
    }

    private void enterPortArea() {
        System.out.println(this + " : entered port area.");
        this.port = Port.getInstance();
    }

    private void exitPortArea() {
        this.port = null;
        System.out.println(this + " : exit port area.");
    }


    private Dock moor() throws PortResourceException {
        System.out.println(this + " : trying to recieve free Dock.");
        Dock dock = null;
        if (port != null) {
            dock = port.recieveDock();
            if (dock == null || dock.isBusy()) {
                throw new PortResourceException("Port returns incorrect dock.");
            }
        } else {
            throw new PortResourceException("No available port for this ship.");
        }
        dock.occupyDock();
        return dock;
    }

    private void unMoor(Dock dock) throws PortResourceException {
        dock.releaseDock();
        port.returnDock(dock);
        System.out.println(this + " : Unmoored succesfully.");
    }


    private List<Container> unloadContainers() {
        List<Container> result = containers;
        containers = new ArrayList<>();
        System.out.printf("%s : %d containers was unloaded. %d Containers left.%n",
                this, result.size(), containers.size());
        return result;
    }

    private void loadContainers(List<Container> containers) throws PortResourceException {
        if (containers != null && containers.size() == containersToLoadAmount) {
            this.containers.addAll(containers);
            System.out.println(this + " : loaded containers in number of " + containers.size());
        } else {
            throw new PortResourceException("Try to load incorrect containers.");
        }
    }

    @Override
    public String toString() {
        return "Ship{" + ID + '}';
    }

    @Override
    public Ship call() {
        try {
            System.out.println("PORT_DISPATCHER : Accepted : " + this);
            TimeUnit.SECONDS.sleep(1);
            enterPortArea();
            Dock freeDock = moor();
            System.out.println(this + " : Succesfully moored to "+ freeDock);
            if (containers.size() > 0) {
                System.out.println(this + " : Trying to unload containers in number of " + containers.size());
                TimeUnit.SECONDS.sleep(1);
                port.storeContainers(unloadContainers());
            }
            if (containersToLoadAmount > 0) {
                System.out.println(this + " : Trying to load containers in number of " + containersToLoadAmount);
                TimeUnit.SECONDS.sleep(1);
                loadContainers(port.recieveContainers(containersToLoadAmount));
            }
            System.out.println(this + " : Work is finished ready to unmoor.");
            unMoor(freeDock);
            exitPortArea();
        } catch (PortResourceException | InterruptedException e) {
            logger.log(Level.FATAL, e);
            throw new RuntimeException(e);
        }
        return this;
    }
}
