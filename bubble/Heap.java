import java.util.ArrayList;
import java.util.List;
public class Heap{ 
	public int[] run(int[] src) {  
		// N-i 指向未排序区的最后一位  
		return heapsort(src);
	}
	int[] heapsort(int[] src){     
		 HeapInn inn = new HeapInn();
		 for(int j=0;j<src.length;j++)
		 	inn.insert(src[j]);
		 int[] r = new int[src.length];
		 int k = 0;		 
		 while(inn.hasMore())
		 	r[k++] = inn.remove();
		 return r ;
	}  
 	public static int[] convertIntegers(Integer[] integers)
	{
		int[] ret = new int[integers.length];
		for (int i=0; i < ret.length; i++)
		{
		    ret[i] = integers[i].intValue();
		}
		return ret;
	}
}
