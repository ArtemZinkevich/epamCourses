package com.rtmznk.railway.comparator;

import com.rtmznk.railway.entity.Wagon;

import java.util.Comparator;

/**
 * Created by RTM on 03.03.2017.
 */
public class WagonTypeComparator implements Comparator<Wagon> {
    @Override
    public int compare(Wagon o1, Wagon o2) {
        return o1.getWagonType().ordinal() > o2.getWagonType().ordinal() ? 1 :
                o1.getWagonType().ordinal() < o2.getWagonType().ordinal() ? -1 : 0;

    }
}
