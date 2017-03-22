package com.rtmznk.port.entity;

import com.rtmznk.port.creator.ContainerCreator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

/**
 * Created by RTM on 22.03.2017.
 */
public class PortStorageTracker extends Thread {
    private static Logger logger = LogManager.getLogger(PortStorageTracker.class);
    private Port trackedPort;

    public PortStorageTracker() {
        this.trackedPort = Port.getInstance();
        this.setDaemon(true);
    }

    private boolean isStorageFull() {
        return trackedPort.getStorage().size() >= Port.getMaxStorageSize() * 4 / 5;
    }

    private boolean isStorageEmpty() {
        return trackedPort.getStorage().size() <=
                (Port.getMaxStorageSize() / 10 > 5 ? Port.getMaxStorageSize() / 10 : 5);
    }

    private void makeStorageHalfEmpty() {
        if (isStorageFull()) {
            trackedPort.recieveContainers(Port.getMaxStorageSize() / 2);
        } else if (isStorageEmpty()) {
            int amountToAdd = (Port.getMaxStorageSize() - trackedPort.getStorage().size()) / 2;
            trackedPort.storeContainers(ContainerCreator.createContainerList(amountToAdd));
        }
    }

    @Override
    public void run() {
        while (true) {
            trackedPort.lockStorage();
            System.out.println("STORAGE_TRACKER : Working.");
            makeStorageHalfEmpty();
            trackedPort.unlockStorage();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                logger.log(Level.ERROR, "StorageTracker stop working.", e);
            }
        }
    }
}
