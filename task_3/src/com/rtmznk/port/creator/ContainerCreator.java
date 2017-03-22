package com.rtmznk.port.creator;

import com.rtmznk.port.entity.Container;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RTM on 21.03.2017.
 */
public class ContainerCreator {

    public static List<Container> createContainerList(int amount) {
        List<Container> result = new ArrayList<>();
        for (int i = 1; i <= amount; i++) {
            result.add(new Container());
        }
        return result;
    }
}
