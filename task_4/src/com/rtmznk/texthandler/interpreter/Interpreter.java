package com.rtmznk.texthandler.interpreter;

import com.rtmznk.texthandler.composite.TextComponent;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by RTM on 06.04.2017.
 */
public class Interpreter {
    private static Logger logger = LogManager.getLogger(Interpreter.class);

    public TextComponent calculate(List<String> rpnParts, int i, int j) {
        Context context = new Context(i, j);
        return null;
    }

    private List<Operation> recieveOperationsList(List<String> rpnParts) {
        List<Operation> operations = new ArrayList<>();
        for (String lexeme : rpnParts) {
            char temp = lexeme.charAt(0);
            switch (temp) {
                case 'i':
                    operations.add((Context context) -> {
                        context.pushValue("i");
                    });
                    break;
                case 'j':
                    operations.add((Context context) -> {
                        context.pushValue("j");
                    });
                    break;
                case '+':
                    operations.add((Context context) -> {
                        int firstAddendum = reciveInt(context);
                        int secondAddendum = reciveInt(context);
                        context.pushValue(String.valueOf(firstAddendum + secondAddendum));
                    });
                    break;
                case '-':
                    operations.add((Context context) -> {
                        int subtrahend = reciveInt(context);
                        int minuend = reciveInt(context);
                        context.pushValue(String.valueOf(minuend - subtrahend));
                    });
                    break;
                case '*':
                    operations.add((Context context) -> {
                        context.pushValue(String.valueOf(reciveInt(context) * reciveInt(context)));
                    });
                    break;
                case '/':
                    operations.add((Context context) -> {
                        int divider = reciveInt(context);
                        int dividend = reciveInt(context);
                        context.pushValue(String.valueOf(dividend / divider));
                    });
                    break;
                case '#':
                    operations.add((Context context) -> {
                        String contextTop = context.popValue();
                        if (contextTop.equals("i")) {
                            context.pushValue(String.valueOf(context.getAndAddI()));
                        } else if (contextTop.equals("j")) {
                            context.pushValue(String.valueOf(context.getAndAddJ()));
                        } else {
                            logger.log(Level.WARN, "Incrementation operation for digit has no sense");
                            context.pushValue(String.valueOf(Integer.parseInt(contextTop)));
                        }
                    });
                    break;
                case '@':
                    operations.add((Context context) -> {
                        String contextTop = context.popValue();
                        if (contextTop.equals("i")) {
                            context.pushValue(String.valueOf(context.addAndGetI()));
                        } else if (contextTop.equals("j")) {
                            context.pushValue(String.valueOf(context.addAndGetJ()));
                        } else {
                            logger.log(Level.WARN, "Incrementation operation for digit has no sense");
                            context.pushValue(String.valueOf(Integer.parseInt(contextTop)));
                        }
                    });
                    break;
                case '$':
                    operations.add((Context context) -> {
                        String contextTop = context.popValue();
                        if (contextTop.equals("i")) {
                            context.pushValue(String.valueOf(context.deductAndGetI()));
                        } else if (contextTop.equals("j")) {
                            context.pushValue(String.valueOf(context.deductAndGetJ()));
                        } else {
                            logger.log(Level.WARN, "Decrement operation for digit has no sense");
                            context.pushValue(String.valueOf(Integer.parseInt(contextTop)));
                        }
                    });
                    break;
                case '&':
                    operations.add((Context context) -> {
                        String contextTop = context.popValue();
                        if (contextTop.equals("i")) {
                            context.pushValue(String.valueOf(context.getAndDeductI()));
                        } else if (contextTop.equals("j")) {
                            context.pushValue(String.valueOf(context.getAndDeductJ()));
                        } else {
                            logger.log(Level.WARN, "Decrement operation for digit has no sense");
                            context.pushValue(String.valueOf(Integer.parseInt(contextTop)));
                        }
                    });
                    break;
                default:
                    Scanner scan = new Scanner(lexeme);
                    if (scan.hasNextInt()) {
                        operations.add((Context context) -> {
                            context.pushValue(String.valueOf(scan.nextInt()));
                        });
                    }
            }
        }
        return null;
    }

    private int reciveInt(Context c) {
        String contextTop = c.popValue();
        int result;
        if (contextTop.equals("i")) {
            result = c.getI();
        } else if (contextTop.equals("j")) {
            result = c.getJ();
        } else {
            result = Integer.parseInt(contextTop);
        }
        return result;
    }
}


