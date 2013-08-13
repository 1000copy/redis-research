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
