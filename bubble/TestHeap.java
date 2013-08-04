import static org.junit.Assert.*;
import junit.framework.TestCase; 
import java.util.Arrays;

public class TestHeap extends TestCase  { 
	public TestHeap(){}
    // public void test(){ 
    //     Heap c=new Heap(); 
    //     int[] src = {3,4,2,5,1};
    //     // int[] dst =c.run(src); 
    //     c.run(src); 
    //     // assertArrayEquals(new int[]{1,2,3,4,5},src); 
    // } 
    public void test(){ 
        HeapInn c=new HeapInn(); 
        int[] src = {3,4,2,5,1};
        // for(int i= 0;i<src.length;i++){
        System.out.println(src[0]);
        for(int i= 0;i<2;i++){
        	c.insert(src[i]);
        }
        int r = c.remove();
        System.out.println(Arrays.toString(c.getInner()));
        // assertEquals(1,r); 
    }
}
