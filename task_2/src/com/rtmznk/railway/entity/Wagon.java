package com.rtmznk.railway.entity;


import com.rtmznk.railway.entity.exception.CreatingEntityException;

import java.util.Arrays;

/**
 * Created by RTM on 03.03.2017.
 */
public class Wagon extends RailwayRollingStock {
    private int wagonNumber;
    private WagonType wagonType;
    private int passengerAmount;
    private int luggageWeight;

    @Override
    public String toString() {
        return "Wagon{" +
                "wagonNumber=" + wagonNumber +
                ", wagonType=" + wagonType +
                ", passengerAmount=" + passengerAmount +
                ", luggageWeight=" + luggageWeight +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Wagon wagon = (Wagon) o;

        if (wagonNumber != wagon.wagonNumber) return false;
        if (passengerAmount != wagon.passengerAmount) return false;
        if (luggageWeight != wagon.luggageWeight) return false;
        return wagonType == wagon.wagonType;
    }

    @Override
    public int hashCode() {
        int result = wagonNumber;
        result = 31 * result + (wagonType != null ? wagonType.hashCode() : 0);
        result = 31 * result + passengerAmount;
        result = 31 * result + luggageWeight;
        return result;
    }

    public Wagon(int wagonType, int passengerAmount, int luggageWeight) throws CreatingEntityException {
        this(wagonType);
        this.passengerAmount = passengerAmount;
        this.luggageWeight = luggageWeight;
    }

    public Wagon(int wagonType, int passengerAmount) throws CreatingEntityException {
        this(wagonType);
        if (passengerAmount <= this.wagonType.getMaxPassengers()) {
            this.passengerAmount = passengerAmount;
        } else {
            throw new CreatingEntityException("passenger amount more than wagon capacity");
        }
    }

    public Wagon(int wagonType) throws CreatingEntityException {
        if (wagonType < WagonType.values().length && wagonType >= 0) {
            this.wagonType = WagonType.values()[wagonType];
        } else {
            throw new CreatingEntityException("Incorrect wagon type");
        }

    }

    public Wagon(int... args) throws CreatingEntityException {
        if (args != null && args.length <= 3 && args.length >= 0) {
            if (args[0] >= 0 && args[0] < WagonType.values().length) {
                this.wagonType = WagonType.values()[args[0]];
            } else {
                throw new CreatingEntityException("Wrong wagon Type : " + args[0]);
            }
            if (args.length == 2 && args[1] <= wagonType.getMaxPassengers() && args[1] >= 0) {
                this.passengerAmount = args[1];
            } else {
                throw new CreatingEntityException("passengers amount more than capacity: " + args[1]);
            }
            if (args.length == 3) {
                this.luggageWeight = args[3];
            }
        } else {
            throw new CreatingEntityException("Wrong arguments : " + Arrays.asList(args));
        }
    }

    public int getWagonNumber() {
        return wagonNumber;
    }

    public void setWagonNumber(int wagonNumber) {
        this.wagonNumber = wagonNumber;
    }

    public int getPassengerAmount() {
        return passengerAmount;
    }

    public void setPassengerAmount(int passengerAmount) {
        this.passengerAmount = passengerAmount;
    }

    public int getLuggageWeight() {
        return luggageWeight;
    }

    public void setLuggageWeight(int luggageWeight) {
        this.luggageWeight = luggageWeight;
    }

    public WagonType getWagonType() {
        return wagonType;
    }
}
