package com.rmznk.xmltask.factory;

import com.rmznk.xmltask.dom.FlowerDOMBuilder;
import com.rmznk.xmltask.sax.FlowersSAXBuilder;
import com.rmznk.xmltask.stax.FlowerStAXBuilder;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * Created by RTM on 27.04.2017.
 */
public class FlowerBuilderFactory {
    private static Logger logger = LogManager.getLogger();
    public AbstractFlowerBuilder createFlowerBuilder(String typeParser) {
        TypeParser type = TypeParser.valueOf(typeParser.toUpperCase());
        switch (type) {
            case DOM:
                return new FlowerDOMBuilder();
            case STAX:
                return new FlowerStAXBuilder();
            case SAX:
                return new FlowersSAXBuilder();
            default:
                logger.log(Level.ERROR,"Parser of this type don't exist. Choose on of: SAX/STAX/DOM");
                return null;
        }
    }

    private enum TypeParser {
        SAX, STAX, DOM
    }
}
