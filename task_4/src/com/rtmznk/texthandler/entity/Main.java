package com.rtmznk.texthandler.entity;

import com.rtmznk.texthandler.composite.TextComponent;
import com.rtmznk.texthandler.interpreter.Context;
import com.rtmznk.texthandler.interpreter.Interpreter;
import com.rtmznk.texthandler.operator.TextOperator;
import com.rtmznk.texthandler.parser.IntoParagraphParser;


import java.util.regex.Pattern;

/**
 * Created by RTM on 02.04.2017.
 */
public class Main {
    public Main() {
        throw new RuntimeException();
    }

    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("(?<=[\\s-])\\p{Punct}*\\b\\w*\\b\\p{Punct}*");
        String text = "\tIt has survived not only five centuries, but also the leap into 13+ i-- electronic\n" +
                " typesetting, remaining 3+5 essentially 6+9*(3-4) unchanged. It was popularised\n" +
                " in the 5*(1*2*(3*(4*(5- --j + 4)-3)-2)-1) with the release of Letraset sheets containing\n" +
                " Lorem Ipsum passages, and more recently with desktop publishing software like Aldus\n" +
                " PageMaker including versions of Lorem Ipsum.\n" +
                "\n" +
                "\tIt is a long established fact that a reader will be distracted by the readable\n" +
                " content of a page when looking at its layout. The point of using (71-(2*i--*(3*(2-1/2*2)-\n" +
                " 2)-10/2))*++i Ipsum is that it has a more-or-less normal distribution of letters, as\n" +
                " opposed to using 'Content here, content here', making it look like readable English.\n" +
                "\n" +
                "\tIt is a (-5+1/2*(2+5*2- --j))*1200 established fact that a reader will be of a page\n" +
                " when looking at its layout.\n" +
                "\n" +
                "\tBye.";
        IntoParagraphParser textParser = new IntoParagraphParser();
        TextComponent components = textParser.doChain(text);
        String completedText =components.receiveText();
        System.out.println(completedText);
        TextOperator operator = new TextOperator();

        operator.calculateText(components,new Interpreter(),new Context(1,1));
        completedText =components.receiveText();
        System.out.println(completedText);
//       for(TextComponent c:components.receiveComponents(TextChildLevel.MATH)){
//           System.out.println(c.receiveText());
//       }


       /* Matcher matcher = pattern.matcher(text);
        int i = 0;
        String k = "\tIt is a (-5+1/2*(2+5*2- --j))*1200 established fact that a reader will be of a page\n" +
                " when looking at its layout.";
        while (matcher.find()) {
            System.out.println(i + "___________________________________________");
            System.out.println(matcher.group());
            i++;
        }
*/
    }
}
