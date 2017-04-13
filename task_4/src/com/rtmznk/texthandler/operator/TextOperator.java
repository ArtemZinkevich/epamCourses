package com.rtmznk.texthandler.operator;

import com.rtmznk.texthandler.composite.TextChildLevel;
import com.rtmznk.texthandler.composite.TextComponent;
import com.rtmznk.texthandler.interpreter.Context;
import com.rtmznk.texthandler.interpreter.Interpreter;

import java.util.List;

/**
 * Created by RTM on 13.04.2017.
 */
public class TextOperator {
    public void calculateText(TextComponent text, Interpreter interpreter, Context context) {
        receiveMathExpressions(text).forEach((TextComponent component) -> {
            String expression = component.receiveText().trim();
            System.out.println(expression);
            List<String> rpnList = ExpressionOperator.receiveRpnList(expression);
            System.out.println(rpnList);
            TextComponent result = interpreter.executeInterpretation(rpnList, context);
            component.removeChilds();
            component.add(result);
            context.resetContext();
        });
    }

    private List<TextComponent> receiveMathExpressions(TextComponent text) {
        return text.receiveComponents(TextChildLevel.MATH);
    }
}
