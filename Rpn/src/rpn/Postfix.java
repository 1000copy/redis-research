/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpn;

import java.util.Stack;

public class Postfix {

    public int priority(char ch) {
        if (ch == '^') {
            return 3;
        }
        if (ch == '/' || ch == '*') {
            return 2;
        }
        if (ch == '+' || ch == '-') {
            return 1;
        }
        return 0;
    }

    public String toPostfix(String in) {
        String copy = in + ")";
        Stack s = new Stack();
        s.push('(');
        int i = 0, l = copy.length();
        char ch;
        String r = "";
        while (i < l) {
            ch = copy.charAt(i);
            if (isLetter(ch)) {
                while (isLetter(ch) && i < l) {
                    r += ch;                    
                    i++;
                    ch = copy.charAt(i);
                }
                r += " ";
                continue;
            } else if (ch == '(') {
                s.push(ch);
            } else if (ch == ')') {
                while (s.peek() != '(') {
                    r += s.pop();
                }
                s.pop();
            } else {
                while (priority(ch) <= priority((char) s.peek())) {
                    r += s.pop();
                }
                s.push(ch);
            }
            i++;
        }
        return r;
    }

    private boolean isLetter(char ch) {
        return ch != '+'
                && ch != '-'
                && ch != '*'
                && ch != '/'
                && ch != '('
                && ch != '^'
                && ch != ')';
    }
}
 