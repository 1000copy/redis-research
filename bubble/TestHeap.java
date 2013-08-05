import static org.junit.Assert.*;
import junit.framework.TestCase; 
import java.util.Arrays;

public class TestHeap extends TestCase  { 
	public TestHeap(){}
    public void test(){ 
        HeapInn c=new HeapInn(); 
        int[] src = {3,4,2,7,5,9,8,11};
        // for(int i= 0;i<src.length;i++){
        for(int i= 0;i<src.length;i++){
        	c.insert(src[i]);
        }        
        try{
            assertEquals(c.remove(),2);
            assertEquals(c.remove(),3);
            assertEquals(c.remove(),4);
            assertEquals(c.remove(),5);
            assertEquals(c.remove(),7);
        }catch(Exception e ){System.out.println(e.getMessage());}    
    }
     public void test1(){
        Heap h = new Heap ();
        try{
        assertArrayEquals(new int[]{2,3,4,5,7,8,9,11},h.heapsort(new int[]{3,4,2,7,5,9,8,11}));
          }catch(Exception e ){System.out.println(e.getMessage());}
        // assertEquals(1,r);         
     }

}
