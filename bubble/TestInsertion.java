import junit.framework.TestCase; 
import static org.junit.Assert.*;
import java.util.Arrays;

public class TestInsertion extends TestCase  { 
	public TestInsertion(){}
    public void test(){ 
        Insertion c=new Insertion(); 
        int[] src = {3,4,2,5,1};
        c.run(src); 
        assertArrayEquals(new int[]{1,2,3,4,5},src); 
        // System.out.println(Arrays.toString(src));
    } 
}
