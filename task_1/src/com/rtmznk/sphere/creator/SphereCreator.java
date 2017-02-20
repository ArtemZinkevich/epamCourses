package com.rtmznk.sphere.creator;

import com.rtmznk.sphere.parser.ParametrStringParser;
import com.rtmznk.sphere.parser.ParsingResult;
import com.rtmznk.sphere.reader.InputFileReader;

/**
 * Created by RTM on 17.02.2017.
 */
public class SphereCreator {
    public void createSphere(){
        ParametrStringParser parser= new ParametrStringParser();
        for(ParsingResult parsingResult:parser.getParsingResultList(InputFileReader.readFileToStringList())){

        }
    }
}
