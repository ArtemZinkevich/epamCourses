package com.rtmznk.railway.operator;

import com.rtmznk.railway.entity.Train;
import com.rtmznk.railway.entity.Wagon;
import com.rtmznk.railway.generator.WagonNumberGen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by RTM on 03.03.2017.
 */
public class TrainOperator {

    public static List<Wagon> getWagonsWithPassengersIn(Train train, int begin, int end) {
        List<Wagon> resultList = new ArrayList<>();
        for (Wagon wagon : train.getWagons()) {
            if (wagon.getPassengerAmount() >= begin && wagon.getPassengerAmount() <= end) {
                resultList.add(wagon);
            }
        }
        return resultList;
    }

    public static int calculatePassengers(Train train) {
        int passengersAmount = 0;
        for (Wagon wagon : train.getWagons()) {
            passengersAmount += wagon.getPassengerAmount();
        }
        return passengersAmount;
    }

    public static int calculateLuggage(Train train) {
        int luggageWeight = 0;
        for (Wagon wagon : train.getWagons()) {
            luggageWeight += wagon.getLuggageWeight();
        }
        return luggageWeight;
    }

    public static void sortTrainWagons(Train train, Comparator<Wagon> comparator) {
        Collections.sort(train.getWagons(), comparator);
    }

    public static void numerateWagons(Train train) {
        if (train.getWagons()!= null) {
            WagonNumberGen.refreshNumber();
            for (Wagon wagon : train.getWagons()) {
                wagon.setWagonNumber(WagonNumberGen.getNextWagonNumber());
            }
            WagonNumberGen.refreshNumber();
        }
    }
}
