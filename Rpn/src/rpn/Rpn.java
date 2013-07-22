package rpn;

import java.util.Stack;
//"1. read src尾部加上 ）
// 2. 在Stack内push一个（" 这样的表达式和原来的等效，但是不必分支判断stack是否非空。牛逼！
public class Rpn {

    public static void main(String[] args) {
        test_fix1();
    }

    private static void test_fix1() {
        Postfix p = new Postfix();
        try {
           System.out.print(p.toPostfix("10*(2+3)"));
           System.out.println();
            System.out.print(p.toPostfix("1*(2+3+4)"));
           System.out.println();
           System.out.print(p.toPostfix("1+2+3"));
           System.out.println();
           System.out.print(p.toPostfix("1*(2+3)").equals("123+*"));
            System.out.print(p.toPostfix("1*(2+3+4)").equals("123+4+*"));
            System.out.print(p.toPostfix("1+2+3").equals("12+3+"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

class Postfix {

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
