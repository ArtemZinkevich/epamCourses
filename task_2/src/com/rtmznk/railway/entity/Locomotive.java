package com.rtmznk.railway.entity;

import com.rtmznk.railway.entity.exception.CreatingEntityException;

/**
 * Created by RTM on 02.03.2017.
 */
public class Locomotive extends RailwayRollingStock {
    @Override
    public String toString() {
        return "Locomotive{" +
                "engine=" + engine +
                '}';
    }

    private Engine engine;

    public Locomotive(Engine engine) {
        this.engine = engine;
    }

    public Locomotive(int engineType,int enginePowerKw) throws CreatingEntityException {
        this.engine = new Engine(engineType,enginePowerKw);
    }

    public Locomotive(int... args) throws CreatingEntityException {
        if(args.length==2){
            this.engine = new Engine(args[0],args[1]);
        }else{
            throw new CreatingEntityException("Wrong parametrs length");
        }
    }

    public Engine getEngine() {
        return engine;
    }
}
