package com.rtmznk.railway.entity;

/**
 * Created by RTM on 02.03.2017.
 */
public class Engine {
    private EngineType type;
    private int powerKW;

    public Engine(int type, int powerKW) throws CreatingEntityException {
        if (type < EngineType.values().length && type >= 0) {
            this.type = EngineType.values()[type];
        } else {
            throw new CreatingEntityException("Incorrect engine type");
        }
        if (powerKW > 0) {
            this.powerKW = powerKW;
        } else {
            throw new CreatingEntityException("Engine power less than 0");
        }
    }

    public Engine(EngineType type, int powerKW) {
        this.type = type;
        this.powerKW = powerKW;
    }

    public EngineType getType() {
        return type;
    }

    public int getPowerKW() {
        return powerKW;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Engine engine = (Engine) o;

        if (powerKW != engine.powerKW) return false;
        return type == engine.type;
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + powerKW;
        return result;
    }

    @Override

    public String toString() {
        return "Engine{" +
                "type=" + type +
                ", powerKW=" + powerKW +
                '}';
    }
}
