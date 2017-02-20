package com.rtmznk.sphere.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by RTM on 17.02.2017.
 */
public class ParametrStringParser {
    static final Logger logger= LogManager.getLogger(ParametrStringParser.class);
    private int parseInt(String s) {
        return Integer.parseInt(s);
    }

    public List<ParsingResult> getParsingResultList(List<String> list) {
        Pattern pattern = Pattern.compile("^\\s*(\\d+\\s){3}\\d\\s*$");
        List<ParsingResult> paramList = new ArrayList<>();
        for (String string : list) {
            Matcher matcher = pattern.matcher(string);
            if (matcher.matches()) {
                String resultString = string.trim();
                String[] paramArray = resultString.split(" ");
                paramList.add(new ParsingResult(parseInt(paramArray[0]), parseInt(paramArray[1]),
                        parseInt(paramArray[2]), parseInt(paramArray[3])));
            }
            else{
                logger.info("incorrect line in file +" +string);
            }
        }
        return paramList;
    }
}
