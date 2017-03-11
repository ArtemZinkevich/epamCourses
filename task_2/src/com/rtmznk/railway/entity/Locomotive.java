package com.rtmznk.railway.entity;

/**
 * Created by RTM on 02.03.2017.
 */
public class Locomotive extends RailwayRollingStock {
    private Engine engine;

    public Locomotive(int engineType, int enginePowerKw) throws CreatingEntityException {
        this.engine = new Engine(engineType, enginePowerKw);
    }

    public Locomotive(int[] args) throws CreatingEntityException {
        if (args.length == 2) {
            this.engine = new Engine(args[0], args[1]);
        } else {
            throw new CreatingEntityException("Wrong parametrs length : " + args.length);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Locomotive that = (Locomotive) o;

        return engine != null ? engine.equals(that.engine) : that.engine == null;
    }

    @Override
    public int hashCode() {
        return engine != null ? engine.hashCode() : 0;
    }

    @Override

    public String toString() {
        return "Locomotive{" +
                "engine=" + engine +
                '}';
    }

}
