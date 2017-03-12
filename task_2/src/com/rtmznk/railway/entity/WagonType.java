package com.rtmznk.railway.entity;

/**
 * Created by RTM on 02.03.2017.
 */
public enum WagonType {
    LIT(18, true), COMPARTMENT(36, true), PLATZKART(54, true), DAY(81, true),
    SITTING(62, true), BAGGAGE(0, true), BUFFET(0, true),
    UNIVERSAL_FREIGHT(0, false), SPECIALIZED_FREIGHT(0, false);

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
