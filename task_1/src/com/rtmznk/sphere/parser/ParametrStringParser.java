package com.rtmznk.sphere.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by RTM on 17.02.2017.
 */
public class ParametrStringParser {
    private static final String FOUR_DIGIT_PER_SPACE_TEMPLATE = "^\\s*(\\d+\\s){3}\\d+\\s*$";
    private static Logger logger = LogManager.getLogger(ParametrStringParser.class);

    public List<int[]> getParsingResultList(List<String> list) {
        Pattern pattern = Pattern.compile(FOUR_DIGIT_PER_SPACE_TEMPLATE);
        List<int[]> paramList = new ArrayList<>();
        for (String string : list) {
            Matcher matcher = pattern.matcher(string);
            if (matcher.matches()) {
                Scanner scanner = new Scanner(string);
                int[] array = new int[4];
                for (int i = 0; i < array.length; i++) {
                    if (scanner.hasNextInt()) {
                        array[i] = scanner.nextInt();
                    }
                }
                paramList.add(array);
            } else {
                logger.warn("incorrect line : " + string);
            }
        }
        return paramList;
    }
}
