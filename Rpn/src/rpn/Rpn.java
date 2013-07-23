package rpn;

import java.math.BigDecimal;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
//"1. read src尾部加上 ）
// 2. 在Stack内push一个（" 这样的表达式和原来的等效，但是不必分支判断stack是否非空。牛逼！

public class Rpn {

    public static void main(String[] args) {
        test_fix1();
        test_fix2();
    }

    private static void test_fix1() {
        Postfix p = new Postfix();
        try {
           assertEq("10 2 3 +*",p.toPostfix("10*(2+3)"));
           assertEq("1 2 3 +4 +*",p.toPostfix("1*(2+3+4)"));           
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    static void assertEq(String a,String b,String err){
        if (!a.equals(b)){
            System.out.println(String.format("Eq Assert Failure:%s,%s,%s",a,b,err));
//           System.out.println();
        }
     }
    static void assertEq(String a,String b){
        assertEq(a,b,"");
    }
    static void assertEq(BigDecimal a,BigDecimal b){
        assertEq(a.toString(),b.toString(),"");
    }
    private static void test_fix2() {
        try {
            //1+2=3            
            assertEq(new BigDecimal(3),new Expression("1+2").eval());
            //1+2*8=17
            assertEq(new Expression("1+2*8").eval().toString(),"17");        
            Expression exp = new Expression("5*sin(2)");
            assertEq("","Just print RPN:",exp.toRPN());            
            test_prioty_var_func();
            test_comma_flow();            
        } catch (Exception ex) {
            Logger.getLogger(Rpn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // 逗号的处理流程    
    private static void test_comma_flow() {
        // , 的处理 sqrt(1 ,2) = 1 2 sqrt 逗号并不导致 任何pop
        assertEq("1 2 max",new Expression("max(1, 2)").toRPN());             
        // , 的处理 sqrt(1+1 ,2) = 1 1 + 2  sqrt 逗号导致 加号 弹出堆栈
        // , 不入堆栈，但是会弹出堆栈内容到输出，直到左括号
        assertEq("1 1 + 2 max",new Expression("max(1+1, 2)").toRPN());
    }

    private static void test_prioty_var_func() {
        Expression exp;
        // 变量优先于函数，是否为缺陷?
        BigDecimal a = new BigDecimal("3");
        BigDecimal b = new BigDecimal("4");
        BigDecimal c = new BigDecimal("10");
        exp = new Expression("SQRT(a^2 + b^2)").with("a",a).and("b",b).and("SQRT",c);
        assertEq(exp.toRPN(),"10 3 2 ^ 4 2 ^ +");
        exp = new Expression("SQRT(a^2 + b^2)").with("a",a).and("b",b);
        assertEq(exp.toRPN(),"3 2 ^ 4 2 ^ + SQRT");
        try{
            new Expression("SQRT(a^2 + b^2)").with("a",a).and("b",b).and("SQRT",c).eval();
        }catch(Exception e){
            // 多了一个操作数，需要处理异常
        }
    }
}
