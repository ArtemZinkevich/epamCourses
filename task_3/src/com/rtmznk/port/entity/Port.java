package com.rtmznk.port.entity;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by RTM on 20.03.2017.
 */
public class Port {
    private final static int MAX_STORAGE_SIZE = 100;
    private final static int DOCK_AMOUNT = 3;
    private static Logger logger = LogManager.getLogger(Port.class);
    private static AtomicBoolean created = new AtomicBoolean(false);
    private static Lock portLock = new ReentrantLock(true);
    private static Port instance = null;
    private Lock docksLock;
    private Condition docksEmptyCondition;
    private Lock storageLock;
    private Condition storageFullCondition;
    private Condition storageEmptyCondition;

    private Queue<Dock> docks;
    private ArrayList<Container> storage;

    private Port() {
        docksLock = new ReentrantLock(true);
        storageLock = new ReentrantLock(true);
        docksEmptyCondition = docksLock.newCondition();
        storageFullCondition = storageLock.newCondition();
        storageEmptyCondition = storageLock.newCondition();
        docks = new LinkedList<>();
        for (int i = 1; i <= DOCK_AMOUNT; i++) {
            docks.add(new Dock(i));
        }
        storage = new ArrayList<>();
    }

    public static Port getCreated() {
        if (!created.get()) {
            portLock.lock();
            try {
                if (!created.get()) {
                    instance = new Port();
                    created.set(true);
                }
            } finally {
                portLock.unlock();
            }
        }
        return instance;
    }

    public static int getDockAmount() {
        return DOCK_AMOUNT;
    }

    public static int getMaxStorageSize() {
        return MAX_STORAGE_SIZE;
    }

    public ArrayList<Container> getStorage() {
        return storage;
    }

    public Dock recieveDock() {
        Dock result = null;
        docksLock.lock();
        try {
            while (docks.size() <= 0) {
                try {
                    System.out.println("PORT : All dock are occupied. Waiting...");
                    docksEmptyCondition.await();
                } catch (InterruptedException e) {
                    logger.log(Level.ERROR, Thread.currentThread() + " interrupted while waiting.", e);
                }
            }
            result = docks.poll();
        } finally {
            docksLock.unlock();
        }

        return result;
    }

    public void returnDock(Dock dock) {
        docksLock.lock();
        try {
            docks.add(dock);
            System.out.println("PORT : one dock returned.Amount of free docks is : " + docks.size());
            docksEmptyCondition.signal();
        } finally {
            docksLock.unlock();
        }
    }

    public void storeContainers(List<Container> containers) {
        storageLock.lock();
        try {
            System.out.printf("PORT : trying to store %d containers.%n", containers.size());
            while (MAX_STORAGE_SIZE - storage.size() <= containers.size()) {
                System.out.println("PORT : storage is full.Waiting for unloading.");
                try {
                    storageFullCondition.await();
                } catch (InterruptedException e) {
                    logger.log(Level.ERROR, Thread.currentThread() + " interrupted while waiting.", e);
                }
            }
            storage.addAll(containers);
            System.out.printf("PORT : %d containers succesfully added. Current containers amount is %d.%n",
                    containers.size(), storage.size());
            storageEmptyCondition.signalAll();
        } finally {
            storageLock.unlock();
        }
    }

    public List<Container> recieveContainers(int amount) {
        storageLock.lock();
        ArrayList<Container> result = null;
        try {
            System.out.printf("PORT : trying to recive %d containers.%n", amount);
            while (storage.size() < amount) {
                System.out.println("PORT : not enough containers.Waiting...");
                try {
                    storageEmptyCondition.await();
                } catch (InterruptedException e) {
                    logger.log(Level.ERROR, Thread.currentThread() + " interrupted while waiting.", e);
                }
            }
            result = new ArrayList<>();
            for (int i = 0; i < amount; i++) {
                result.add(storage.remove(0));
            }
            System.out.printf("PORT : %d containers succesfully removed. Current containers amount is %d.%n",
                    result.size(), storage.size());
            storageFullCondition.signalAll();
        } finally {
            storageLock.unlock();
        }
        return result;
    }

    public void lockStorage() {
        storageLock.lock();
    }

    public void unlockStorage() {
        storageLock.unlock();
    }

}
