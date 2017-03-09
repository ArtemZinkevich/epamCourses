package com.rtmznk.railway.entity;

import com.rtmznk.railway.generator.WagonNumberGen;
import com.rtmznk.railway.operator.TrainOperator;

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
        TrainOperator.numerateWagons(this);
    }

    public TrainType getTrainType() {
        return trainType;
    }

    public List<Locomotive> getLocomotive() {
        return locomotive;
    }

    public List<Wagon> getWagons() {
        return wagons;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Train train = (Train) o;

        if (trainType != train.trainType) return false;
        if (locomotive != null ? !locomotive.equals(train.locomotive) : train.locomotive != null) return false;
        return wagons != null ? wagons.equals(train.wagons) : train.wagons == null;
    }

    @Override
    public int hashCode() {
        int result = trainType != null ? trainType.hashCode() : 0;
        result = 31 * result + (locomotive != null ? locomotive.hashCode() : 0);
        result = 31 * result + (wagons != null ? wagons.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Train{" +
                "trainType=" + trainType +
                ", locomotive=" + locomotive +
                ", wagons=" + wagons +
                '}';
    }


}
