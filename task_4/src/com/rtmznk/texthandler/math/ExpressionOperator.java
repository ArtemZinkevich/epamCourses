package com.rtmznk.texthandler.math;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by RTM on 06.04.2017.
 */
public class ExpressionOperator {
    private static final String DECREMENT_BEFORE_PATTERN = "--(?=[ij])";
    private static final String DECREMENT_AFTER_PATTERN = "(?<=[ij])--";
    private static final String INCREMENT_BEFORE_PATTERN = "\\+\\+(?=[ij])";
    private static final String INCREMENT_AFTER_PATTERN = "(?<=[ij])\\+\\+";
    private static final String NEGATIVE_DIGIT = "(?<![\\d)])-\\d+";
    private static Map<Character, Integer> priorityMap;

    static {
        priorityMap = new HashMap<>();
        priorityMap.put('(', 0);
        priorityMap.put('+', 1);
        priorityMap.put('-', 1);
        priorityMap.put('*', 2);
        priorityMap.put('/', 2);
        priorityMap.put('#', 3);
        priorityMap.put('@', 3);
        priorityMap.put('$', 3);
        priorityMap.put('&', 3);
    }

    public static void main(String[] args) {
        String mathExpression = "5*(1*2*(3*(4*(5- --j + 4)-3)-2)-1)";

        System.out.println(reciveRpnList(mathExpression));
    }

    private static List<String> reciveRpnList(String mathExpression) {
        List<String> result = new ArrayList<>();
        Deque<Character> deque = new LinkedList<>();
        List<String> negativeDigits = new ArrayList<>();
        mathExpression = mathExpression.replaceAll(INCREMENT_AFTER_PATTERN, "#");
        mathExpression = mathExpression.replaceAll(INCREMENT_BEFORE_PATTERN, "@");
        mathExpression = mathExpression.replaceAll(DECREMENT_BEFORE_PATTERN, "\\$");
        mathExpression = mathExpression.replaceAll(DECREMENT_AFTER_PATTERN, "&");
        Pattern negativeDigit = Pattern.compile(NEGATIVE_DIGIT);
        Matcher negativeMatcher = negativeDigit.matcher(mathExpression);
        while (negativeMatcher.find()) {
            String digit = negativeMatcher.group();
            negativeDigits.add(digit);
            mathExpression = mathExpression.replaceFirst(digit, "N");
            negativeMatcher = negativeMatcher.reset();
        }
        List<String> digits = new ArrayList<>();
        Scanner sc = new Scanner(mathExpression);
        sc.useDelimiter("[^\\d]+");
        while (sc.hasNextInt()) {
            digits.add(String.valueOf(sc.nextInt()));
        }
        for (String d : digits) {
            mathExpression = mathExpression.replaceFirst(d, "D");
        }
        System.out.println(digits);
        mathExpression = mathExpression.replaceAll("\\s", "");
        int ndIndex = 0;
        int dIndex = 0;
        System.out.println(mathExpression);
        for (char ch : mathExpression.toCharArray()) {
            System.out.println(ch);
            if (ch == 'D') {
                result.add(digits.get(dIndex++));
            } else if (ch == 'N') {
                result.add(negativeDigits.get(ndIndex++));
            } else if (ch == '(') {
                deque.push(ch);
            } else if (ch == ')') {
                char current = deque.pop();
                while (current != '(') {
                    result.add(String.valueOf(current));
                    current = deque.pop();
                }
            } else if (ch == 'i' || ch == 'j') {
                result.add(String.valueOf(ch));
            } else {
                int chPriority = priorityMap.get(ch);
                boolean flag = !deque.isEmpty();
                while (flag) {
                    char top = deque.pop();
                    int priority = priorityMap.get(top);
                    if (chPriority < priority) {
                        result.add(String.valueOf(top));
                        flag = !deque.isEmpty();
                    } else {
                        deque.push(top);
                        flag = false;
                    }
                }
                deque.push(ch);
            }
            System.out.println(result);
            System.out.println(deque);
        }
        while (!deque.isEmpty()) {
            result.add(String.valueOf(deque.pop()));
        }
        return result;
    }

}


