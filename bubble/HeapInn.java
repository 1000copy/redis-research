import java.util.ArrayList;
import java.util.List;

public class HeapInn{
	int[] array = new int[200];
	// int index = 0 ;
	int currentIndex = 0;
	public int remove(){
		int root = array[0];
		currentIndex--;
		down(currentIndex);
		return 0;
	}
	public void up(int index){
		int bottom = array[index];
		int _parent = parent(index);
		while (_parent > 0 ){
			if (array[_parent] > bottom){
				array[index] = array[_parent];
				array[_parent] = bottom;
			}
			index = parent(index);
		}
	}
	public void down(int index){
		
	}
	public void insert(int n)
	{
		// array[currentIndex++] = n ;
		array[currentIndex] = n ;
		// System.out.println(array[currentIndex]);
		// System.out.println(currentIndex);
		currentIndex++;
		up(currentIndex);
	}	
	public int []getInner(){
		return array;
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