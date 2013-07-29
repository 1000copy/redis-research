import static org.junit.Assert.*;
import junit.framework.TestCase; 
public class TestBubble extends TestCase  { 
	public TestBubble(){}
    public void test(){ 
        Bubble c=new Bubble(); 
        int[] src = {3,4,2,5,1};
        // int[] dst =c.run(src); 
        c.run(src); 
        assertArrayEquals(new int[]{1,2,3,4,5},src); 
    } 
}
