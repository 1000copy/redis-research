import junit.framework.TestCase; 
import junit.framework.Test; 
import junit.textui.TestRunner; 
import junit.framework.TestSuite; 

//testcase
public class TestAll extends TestSuite { 
    public static Test suite() { 
        TestSuite suite = new TestSuite("TestSuite Test"); 
        suite.addTestSuite(TestCalcuator.class);          
        suite.addTestSuite(TestBubble.class);
        suite.addTestSuite(TestInsertion.class);
        suite.addTestSuite(TestHeap.class);
        return suite; 
    } 
    public static void main(String args[]){ 
        try{
            TestRunner.run(suite()); 
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    } 
}
