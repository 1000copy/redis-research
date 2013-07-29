package rpn;

import java.math.BigDecimal;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
//"1. read src尾部加上 ）
// 2. 在Stack内push一个（" 这样的表达式和原来的等效，但是不必分支判断stack是否非空。牛逼！

public class Rpn {
//static int[]  insertion( int a[], int n ) {
    /* Bubble sort for integers */
public static void main(String[] args) {
        int [] a = {1,3,2,5,4};
//        int [] b = insertion(a,5);
//        assertEq(b[2],3);
        bubble(a,5);
//        test_fix1();
//        test_fix2();
//        Expression e = new Expression("1.6*10/2");
//    try {
//        System.out.println(e.eval());
//    } catch (Exception ex) {
//        Logger.getLogger(Rpn.class.getName()).log(Level.SEVERE, null, ex);
//    }
    }

    static void swap(int a,int b)   {  }

// int [] a = {1,3,2,5,4};
// 
static void bubble( int a[], int n )
/* Pre-condition: a contains n items to be sorted */
    {
    int i, j;
    /* Make n passes through the array */
    for(i=0;i<n;i++)
        {
        /* From the first element to the end
           of the unsorted section */
        for(j=1;j<(n-i);j++)
           {
           /* If adjacent items are out of order, swap them */
           if( a[j-1]>a[j] ) {
//               swap(a[j-1],a[j]);
               int x = a[j-1];
               int y = a[j];
               int t; t=x; x=y; y=t;
               a[j-1]=x;
               a[j]=y;
           }
           }
           }
        }
       
    // 插入排序 ：
    static int[]  insertion( int[] a, int n ) {
/* Pre-condition: a contains n items to be sorted */
    int i, j, v;
    /* Initially, the first item is considered 'sorted' */
    /* i divides a into a sorted region, x<i, and an
       unsorted one, x >= i */
    for(i=1;i<n;i++) {
        /* Select the item at the beginning of the
           as yet unsorted section */
        v = a[i];
        /* Work backwards through the array, finding where v 
           should go */
        j = i;
        /* If this element is greater than v,
              move it up one */
        while ( a[j-1] > v ) {
          a[j] = a[j-1]; j = j-1;
          if ( j <= 0 ) break;
          }
        /* Stopped when a[j-1] <= v, so put v at position j */
        a[j] = v;
        }
    return a;
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
    static void assertEq(int a,int b){
        if (a!=b){
            System.out.println(String.format("Eq Assert Failure:%d,%d,%s",a,b));
//           System.out.println();
        }
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
