package com.rtmznk.port.parser;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by RTM on 22.03.2017.
 */
public class ShipStringParser {
    private final static String SHIP_PATTERN = "(?i)^\\s*ship\\s*\\{\\s*\\d{1,3}\\s+\\d{1,3}\\s+\\d{1,3}\\s*}\\s*$";
    private static Logger logger = LogManager.getLogger(ShipStringParser.class);

    public List<int[]> recieveShipParams(List<String> stringList) {
        List<int[]> result = new ArrayList<>();
        for (String string : stringList) {
            Pattern pattern = Pattern.compile(SHIP_PATTERN);
            Matcher matcher = pattern.matcher(string);
            if (matcher.matches()) {
                result.add(recieveAllIntFromString(string));
            } else {
                logger.log(Level.WARN, "Incorrect string : "+ string);
            }
        }
        return result;
    }

    private int[] recieveAllIntFromString(String string) {
        Scanner scanner = new Scanner(string);
        Pattern notDigits = Pattern.compile("\\D+");
        List<Integer> paramlist = new ArrayList<>();
        scanner.useDelimiter(notDigits);
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
