package com.rtmznk.texthandler.operator;

import com.rtmznk.texthandler.composite.TextChildLevel;
import com.rtmznk.texthandler.composite.TextComponent;
import com.rtmznk.texthandler.interpreter.Context;
import com.rtmznk.texthandler.interpreter.Interpreter;

import java.util.Iterator;
import java.util.List;

public class TextOperator {
    public void calculateText(TextComponent text, Interpreter interpreter, Context context) {
        receiveMathExpressions(text).forEach((TextComponent component) -> {
            String expression = component.receiveText().trim();
            List<String> rpnList = ExpressionOperator.receiveRpnList(expression);
            TextComponent result = interpreter.executeInterpretation(rpnList, context);
            component.removeChilds();
            component.add(result);
            context.resetContext();
        });
    }

    public void swapFirstLastLexeme(TextComponent text) {
        List<TextComponent> sentences = text.receiveComponents(TextChildLevel.SENTENCE);
        sentences.forEach((TextComponent component) -> {
            List<TextComponent> lexemes = component.receiveComponents(TextChildLevel.LEXEME);
            TextComponent temp = lexemes.get(0);
            TextComponent last = lexemes.get(lexemes.size() - 1);
            lexemes.set(0, last);
            lexemes.set(lexemes.size() - 1, temp);
            component.removeChilds();
            lexemes.forEach(component::add);
        });
    }

    public void removeLexemes(TextComponent text, char firstCharacter, int length) {
        List<TextComponent> components = text.receiveChilds();
        Iterator<TextComponent> iterator = components.iterator();
        int minOrdinal = Integer.MAX_VALUE;
        while (iterator.hasNext()) {
            TextComponent component = iterator.next();
            if (component.level().ordinal() < minOrdinal) {
                minOrdinal = component.level().ordinal();
            }
            if (component.level().equals(TextChildLevel.LEXEME)) {
                String lexemeText = component.receiveText();
                if (lexemeText.charAt(0) == firstCharacter && lexemeText.length() == length) {
                    component.removeChilds();
                }
            }
        }
        text.removeChilds();
        for (TextComponent component : components) {
            if (component.level().ordinal() == minOrdinal) {
                text.add(component);
            }
        }
    }

    public List<TextComponent> sentencesMinToMaxLexemes(TextComponent text) {
        List<TextComponent> sentences = text.receiveComponents(TextChildLevel.SENTENCE);
        sentences.sort((o1, o2) -> {
            int o1Size = o1.receiveComponents(TextChildLevel.LEXEME).size();
            int o2Size = o2.receiveComponents(TextChildLevel.LEXEME).size();
            return o1Size > o2Size ? 1 : o1Size == o2Size ? 0 : -1;
        });
        return sentences;
    }

    private List<TextComponent> receiveMathExpressions(TextComponent text) {
        return text.receiveComponents(TextChildLevel.MATH);
    }
}
