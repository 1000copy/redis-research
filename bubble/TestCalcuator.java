import junit.framework.TestCase; 
public class TestCalcuator extends TestCase  { 
	public TestCalcuator(){}
    public void testAdd(){ 
        Calcuator calcuator=new Calcuator(); 
        double result=calcuator.add(3,2); 
        assertEquals(5.0,result); 
    } 
}
