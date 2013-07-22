package rpn;

import java.math.BigDecimal;
import java.util.Stack;
//"1. read src尾部加上 ）
// 2. 在Stack内push一个（" 这样的表达式和原来的等效，但是不必分支判断stack是否非空。牛逼！

public class Rpn {

    public static void main(String[] args) {
//        test_fix1();
        test_fix2();
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
    static void assertEq(String a,String b,String err){
        if (!a.equals(b)){
            System.out.println(String.format("not eq:%s,%s,%s",a,b,err));
//           System.out.println();
        }
     }
    static void assertEq(String a,String b){
        assertEq(a,b,"");
    }
    private static void test_fix2() {
//        assertEq(new Expression("1+2").eval().toString(),"3");
//        assertEq(new Expression("1+2*8").eval().toString(),"17");        
//        Expression exp = new Expression("5*sin(2)");
//        assertEq("","ERR:",exp.toRPN());
        BigDecimal a = new BigDecimal("3");
        BigDecimal b = new BigDecimal("4");
        BigDecimal c = new BigDecimal("10");
        Expression exp = new Expression("SQRT(a^2 + b^2)").with("a",a).and("b",b).and("SQRT",c);
        System.out.println(exp.toRPN());
        exp = new Expression("SQRT(a^2 + b^2)").with("a",a).and("b",b);
        System.out.println(exp.toRPN());
        BigDecimal result = new Expression("SQRT(a^2 + b^2)").with("a",a).and("b",b).and("SQRT",c).eval();
        System.out.println(result);
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
 