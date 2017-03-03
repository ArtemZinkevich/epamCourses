package com.rtmznk.railway.reader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by RTM on 17.02.2017.
 */
public class TextFileReader {
    private final static String DEFAULT_FILE_PATH = "data/input.txt";
    private static Logger logger = LogManager.getLogger(TextFileReader.class);

    public List<String> readFileToStringList(String filePath) {
        List<String> list = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(filePath))) {

            list = br.lines().collect(Collectors.toList());

        } catch (IOException | InvalidPathException e) {
            logger.fatal(e);
            throw new RuntimeException(e);
        }

        return list;
    }
}
