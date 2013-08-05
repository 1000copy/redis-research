import java.util.ArrayList;
import java.util.List;

public class HeapInn{
	int[] array = new int[200];
	// int index = 0 ;
	int currentSize = 0;
	public int remove() {
		if (currentSize==0) 
			return -1;
		int root = array[0];
		array[0] = array[currentSize-1];
		currentSize--;
		down(0);
		return root;
	}
	public void up(int index){
		int bottom = array[index];
		while (index > 0 ){
			int _parent = parent(index);
			if (array[_parent] > bottom){
				array[index] = array[_parent];
				array[_parent] = bottom;
			}
			index = parent(index);
		}
	}
	public void down(int index){
		while (index < currentSize/2) {// at least one child
			int min ;
			int _left = left(index);
			int _right = right(index);
			if (array[_left] < array[_right])
				min = _left ;
			else
				min = _right ;
			if (array[min] < array[index]){
				//swap 
				int temp = array[min];
				array[min] = array[index];
				array[index] = temp ;				
				index = min;
			}else
				break;

		}
	}
	public void insert(int n)
	{
		array[currentSize] = n ;
		up(currentSize);
		currentSize++;
	}	
	public int []getInner(){
		return array;
	}
	// public String getInnerString(){
	// 	String r = "";
	// 	for (int i=0;i<currentSize;i++)
	// 		r += array[i] +",";
	// 	return r;
	// }
	public boolean hasMore(){
		return currentSize>0;
	}
	public int left(int i){
		return 2*i+1;
	}
	public int right(int i){
		return 2*i+2;	
	}
	public int parent(int i){
		return (i-1)/2;
	}

}