package com.rtmznk.railway.comparator;


import java.util.Comparator;

/**
 * Created by RTM on 03.03.2017.
 */
public class ChainedComparator<T> {
    public Comparator<T> getChainedComparator(Comparator<T>... args) {
        Comparator<T> comparator=null;
        if (args.length > 0) {
            comparator = args[0];
        }
        for (int i = 1; i < args.length; i++) {
            comparator=comparator.thenComparing(args[i]);
        }
        return comparator;
    }
}
