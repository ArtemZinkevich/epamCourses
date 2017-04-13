package com.rtmznk.texthandler.interpreter;

import com.rtmznk.texthandler.composite.Symbol;
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

    public TextComponent executeInterpretation(List<String> rpnParts, Context context) {
        List<Operation> operations = receiveOperationsList(rpnParts);
        for (Operation o : operations) {
            o.interpret(context);
        }
        String result = context.popValue();
        return new Symbol(result);
    }

    private List<Operation> receiveOperationsList(List<String> rpnParts) {
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
                        double firstAddendum = receiveDouble(context);
                        double secondAddendum = receiveDouble(context);
                        context.pushValue(String.valueOf(firstAddendum + secondAddendum));
                    });
                    break;
                case '-':
                    if (lexeme.length() == 1) {
                        operations.add((Context context) -> {
                            double subtrahend = receiveDouble(context);
                            double minuend = receiveDouble(context);
                            context.pushValue(String.valueOf(minuend - subtrahend));
                        });
                    } else {
                        Scanner scan = new Scanner(lexeme);
                        if (scan.hasNextDouble()) {
                            operations.add((Context context) -> {
                                context.pushValue(String.valueOf(scan.nextDouble()));
                            });
                        }
                    }
                    break;
                case '*':
                    operations.add((Context context) -> {
                        context.pushValue(String.valueOf(receiveDouble(context) * receiveDouble(context)));
                    });
                    break;
                case '/':
                    operations.add((Context context) -> {
                        double divider = receiveDouble(context);
                        double dividend = receiveDouble(context);
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
                            context.pushValue(String.valueOf(Double.parseDouble(contextTop)));
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
                            context.pushValue(String.valueOf(Double.parseDouble(contextTop)));
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
                            context.pushValue(String.valueOf(Double.parseDouble(contextTop)));
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
                            context.pushValue(String.valueOf(Double.parseDouble(contextTop)));
                        }
                    });
                    break;
                default:
                    Scanner scan = new Scanner(lexeme);
                    if (scan.hasNextDouble()) {
                        operations.add((Context context) -> {
                            context.pushValue(String.valueOf(scan.nextDouble()));
                        });
                    }
            }
        }
        return operations;
    }

    private double receiveDouble(Context c) {
        String contextTop = c.popValue();
        double result;
        if (contextTop.equals("i")) {
            result = c.getI();
        } else if (contextTop.equals("j")) {
            result = c.getJ();
        } else {
            result = Double.parseDouble(contextTop);
        }
        return result;
    }
}


