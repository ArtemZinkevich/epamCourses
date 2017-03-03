package com.rtmznk.railway.entity;

/**
 * Created by RTM on 02.03.2017.
 */
public enum WagonType {
    WAGON_LIT(18, true), COMPARTMENT(36, true), PLATZKART(54, true), DAY_COACH(81, true),
    SITTING_COACH(62, true), BAGGAGE_CAR(0, true), BUFFET_CAR(0, true),
    UNIVERSAL_FREIGTH_CAR(0, false), SPECIALIZED_FREIGHT_CAR(0, false);

    private int maxPassengers;
    private boolean isPassengerTrainWagon;

    WagonType(int maxPassengers, boolean isPassengerTrainWagon) {
        this.maxPassengers = maxPassengers;
        this.isPassengerTrainWagon = isPassengerTrainWagon;
    }

    public int getMaxPassengers() {
        return maxPassengers;
    }

    public boolean isPassengerTrainWagon() {
        return isPassengerTrainWagon;
    }
}
