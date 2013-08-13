import java.util.ArrayList;
import java.util.List;
public class ShellSort{ 
	public int _gap(int n){
		 int gap = 0;
	     while (gap<=n)
	     {
	          gap = gap * 3 + 1;
	     } 
	     return gap ;
	}
	public int[] _gap_step(int[] a)
	{
	     int n = a.length;
	     int[] results = new int[a.length];
	     int gap = _gap(n);
	     int i = 0 ;
	     while (gap > 0) 
	     {	        
	     	 results[i++] = gap ;
	         gap = ( gap - 1 ) / 3;
	     }    	     
	     return java.util.Arrays.copyOfRange(results,0,i--);
	 }
	// int[] src = {3,4,2,5,1,6,8,9,10,7};
	gap   4 ,4-10
	public int[] _run(int[] a)
	{
	     int n = a.length;
	     int i, j, temp; 
	     int[] gaps = _gap_step(a);
	     for(int gap : gaps)
	     {
	         for ( i = gap; i < n; i++ )
	         {
	             j = i - gap;
	             // 分组排序
	             temp = a[i];             
	             while (( j >= 0 ) && ( a[j] > temp ))
	             {
	                 a[j + gap] = a[j];
	                 j = j - gap;
	             }
	             a[j + gap] = temp;
	         }	     
	     }    	 	 
	     return a ;
	}
 	public int[] run(int[] a)
	{
	     int n = a.length;
	     int i, j, temp; 
	     int gap = _gap(n);
	     while (gap > 0) 
	     {
	         for ( i = gap; i < n; i++ )
	         {
	             j = i - gap;
	             temp = a[i];             
	             while (( j >= 0 ) && ( a[j] > temp ))
	             {
	                 a[j + gap] = a[j];
	                 j = j - gap;
	             }
	             a[j + gap] = temp;
	         }
	         gap = ( gap - 1 ) / 3;
	     }    
	     return a ;
	 }
}
