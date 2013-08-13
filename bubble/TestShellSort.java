import static org.junit.Assert.*;
import junit.framework.TestCase; 
public class TestShellSort extends TestCase  { 
	public TestShellSort(){}
    public void test(){ 
        ShellSort c=new ShellSort(); 
        int[] src = {3,4,2,5,1,6,8,9,10,7};
        c.run(src); 
        assertArrayEquals(new int[]{1,2,3,4,5,6,7,8,9,10},src); 
        assertArrayEquals(new int[]{1,2,3,4,5,6,7,8,9,10},src); 
        assertEquals(13,c._gap(10)); 
        assertArrayEquals(new int[]{13,4,1}, c._gap_step(src));
        assertArrayEquals(new int[]{1,2,3,4,5,6,7,8,9,10},c._run(src));
        // for(int gap:new int[]{13,4,1}){
        // 	System.out.println(gap);
        // }
    } 
}
