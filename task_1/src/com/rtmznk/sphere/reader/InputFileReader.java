package com.rtmznk.sphere.reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by RTM on 17.02.2017.
 */
public class InputFileReader {
    private final static String FILEPATH = "data/input.txt";

    public static List<String> readFileToStringList() {
        List<String> list = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(FILEPATH))) {

            list = br.lines().collect(Collectors.toList());

        } catch (IOException e) {
            //TODO
        }

return list;
    }
}
