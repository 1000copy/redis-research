import junit.framework.TestCase; 
import junit.framework.Test; 
import junit.textui.TestRunner; 
import junit.framework.TestSuite; 

//testcase
public class TestAll extends TestSuite { 
    public static Test suite() { 
        TestSuite suite = new TestSuite("TestSuite Test"); 
        suite.addTestSuite(TestCalcuator.class);          
        return suite; 
    } 
    public static void main(String args[]){ 
        TestRunner.run(suite()); 
    } 
}
