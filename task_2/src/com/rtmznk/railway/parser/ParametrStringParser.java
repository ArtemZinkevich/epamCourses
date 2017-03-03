package com.rtmznk.railway.parser;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by RTM on 17.02.2017.
 */
public class ParametrStringParser {
    private static final String TRAIN_TYPE_TEMPLATE = "(?i)^\\s*train\\s*\\{\\s*type\\s*:\\s*[0-1]\\s*\\}\\s*$";
    private static final String WAGON_TEMPLATE = "(?i)^\\s*wagon\\s*\\{\\s*type\\s*:\\s*[0-9]\\s*" +
            "(,\\s*passenger\\s*:\\s*\\d{1,2}\\s*)?" +
            "(,\\s*(ba|lu)ggage\\s*:\\s*\\d{1,5}\\s*)?\\}\\s*$";
    private static final String LOCOMOTIVE_TEMPLATE = "(?i)^\\s*locomotive\\s*\\{\\s*enginetype\\s*:\\s*[0-3]\\s*" +
            ",\\s*enginepower\\s*:\\s*\\d{1,4}\\s*\\}\\s*$";
    private static Logger logger = LogManager.getLogger(ParametrStringParser.class);

    public Map<ParametrsType, List<int[]>> getParsingResultMap(List<String> list) {
        Pattern trainPattern = Pattern.compile(TRAIN_TYPE_TEMPLATE);
        boolean isTrainTypeFound = false;
        Pattern wagonPattern = Pattern.compile(WAGON_TEMPLATE);
        Pattern locomotivePattern = Pattern.compile(LOCOMOTIVE_TEMPLATE);
        List<int[]> trainType = new ArrayList<>();
        List<int[]> wagons = new ArrayList<>();
        List<int[]> locomotives = new ArrayList<>();
        HashMap<ParametrsType, List<int[]>> paramMap = new HashMap<>();
        for (String string : list) {
            Matcher trainTypeMatcher = trainPattern.matcher(string);
            Matcher wagonMatcher = wagonPattern.matcher(string);
            Matcher locomotiveMatcher = locomotivePattern.matcher(string);
            if (trainTypeMatcher.matches()) {
                if (!isTrainTypeFound) {
                    int[] result = getAllIntFromString(string);
                    trainType.add(result);
                    isTrainTypeFound = true;
                } else {
                    logger.log(Level.WARN, "Another line with train type definition : " + string);
                }
                continue;
            } else if (wagonMatcher.matches()) {
                wagons.add(getAllIntFromString(string));
                continue;
            } else if (locomotiveMatcher.matches()) {
                locomotives.add(getAllIntFromString(string));
                continue;
            } else {
                logger.log(Level.WARN, "Wrong string : " + string);
            }
        }
        if (!isTrainTypeFound) {
            throw new RuntimeException("Wrong parametrs list. Train Type not Defined");
        }
        paramMap.put(ParametrsType.TRAIN, trainType);
        paramMap.put(ParametrsType.WAGON, wagons);
        paramMap.put(ParametrsType.LOCOMOTIVE, locomotives);
        return paramMap;
    }

    private int[] getAllIntFromString(String string) {
        Scanner scanner = new Scanner(string);
        List<Integer> paramlist = new ArrayList<>();
        while (scanner.hasNextInt()) {
            paramlist.add(scanner.nextInt());
        }
        int[] result = new int[paramlist.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = paramlist.get(i);
        }
        return result;
    }

}

