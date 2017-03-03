package com.rtmznk.railway.entity;

import com.rtmznk.railway.entity.exception.CreatingEntityException;

import java.util.List;

/**
 * Created by RTM on 02.03.2017.
 */
public class Train extends RailwayRollingStock {
    private TrainType trainType;
    private List<Locomotive> locomotive;
    private List<Wagon> wagons;

    public Train(int type, List<Locomotive> locomotive, List<Wagon> wagons) throws CreatingEntityException {
        if (type < TrainType.values().length && type >= 0) {
            trainType = TrainType.values()[type];
        } else {
            throw new CreatingEntityException("Wrong train type");
        }
        this.locomotive = locomotive;
        this.wagons = wagons;
    }

    public TrainType getTrainType() {
        return trainType;
    }

    public List<Locomotive> getLocomotive() {
        return locomotive;
    }

    public void setLocomotive(List<Locomotive> locomotive) {
        this.locomotive = locomotive;
    }

    public List<Wagon> getWagons() {
        return wagons;
    }

    public void setWagons(List<Wagon> wagons) {
        this.wagons = wagons;
    }

    public boolean addLocomotive(Locomotive locomotive) {

        if (this.locomotive != null) {
            return this.locomotive.add(locomotive);
        }
        return false;
    }

    public boolean addWagon(Wagon wagon) {
        if (wagons != null) {
            return wagons.add(wagon);
        }
        return false;
    }

    public boolean removeLocomotive(Locomotive locomotive) {
        if (this.locomotive != null) {
            return this.locomotive.remove(locomotive);
        }
        return false;
    }

    public boolean removeWagon(Wagon wagon) {
        if (this.wagons != null) {
            return wagons.remove(wagon);
        }
        return false;
    }
}
