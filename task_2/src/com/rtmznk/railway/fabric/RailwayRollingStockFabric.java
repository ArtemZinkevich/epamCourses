package com.rtmznk.railway.fabric;

import com.rtmznk.railway.entity.Locomotive;
import com.rtmznk.railway.entity.Train;
import com.rtmznk.railway.entity.Wagon;
import com.rtmznk.railway.entity.exception.CreatingEntityException;
import com.rtmznk.railway.generator.WagonNumberGen;
import com.rtmznk.railway.parser.ParametrsType;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by RTM on 03.03.2017.
 */
public class RailwayRollingStockFabric {
    private static Logger logger = LogManager.getLogger(RailwayRollingStockFabric.class);

    public Wagon createWagon(int... args) throws CreatingEntityException {
        Wagon result=new Wagon(args);
        result.setWagonNumber(WagonNumberGen.getNextWagonNumber());
        return result;
    }

    public List<Wagon> createWagonList(List<int[]> paramList) {
        List<Wagon> wagonList = new ArrayList<>();
        for (int[] params : paramList) {
            try {
                wagonList.add(new Wagon(params));
            } catch (CreatingEntityException e) {
                logger.log(Level.ERROR, e);
            }
        }
        return wagonList;
    }

    public Locomotive createLocomotive(int... args) throws CreatingEntityException {
        return new Locomotive(args);
    }

    public List<Locomotive> createLocomotiveList(List<int[]> paramList) {
        List<Locomotive> locomotives = new ArrayList<>();
        for (int[] params : paramList) {
            try {
                locomotives.add(new Locomotive(params));
            } catch (CreatingEntityException e) {
                logger.log(Level.ERROR, e);
            }
        }
        return locomotives;
    }

    public Train createTrain(HashMap<ParametrsType, List<int[]>> paramMap) throws CreatingEntityException {
        int trainType = paramMap.get(ParametrsType.TRAIN).get(0)[0];
        List<int[]> wagonParamList = paramMap.get(ParametrsType.WAGON);
        List<Wagon> wagonList = createWagonList(wagonParamList);
        List<int[]> locomotiveParamList = paramMap.get(ParametrsType.LOCOMOTIVE);
        List<Locomotive> locomotives = createLocomotiveList(locomotiveParamList);
        return new Train(trainType,locomotives,wagonList);
    }
}
