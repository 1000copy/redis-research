import java.util.ArrayList;
import java.util.List;
public class Heap{ 
	public int[] run(int[] src) {  
		// N-i 指向未排序区的最后一位  
		return heapsort(src);
	}
	int[] heapsort(int[] src){     
		 HeapInn i = new HeapInn();
		 for(int j=0;j<src.length;j++)
		 	i.insert(src[j]);
		 List<Integer> v = new ArrayList();
		 v.add(i.remove());
		 int[] result = new int[src.length];
		 Integer[] rrr= v.toArray(new Integer[0]);
		 return convertIntegers(rrr) ;
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
