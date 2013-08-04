import java.util.ArrayList;
import java.util.List;

public class HeapInn{
	int[] array = new int[200];
	// int index = 0 ;
	int currentIndex = 0;
	public int remove(){
		int root = array[0];
		array[0] = array[currentIndex-1];
		currentIndex--;
		down(currentIndex);
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
		while (index < currentIndex/2) {// at least one child
			int large ;
			int _left = left(index);
			int _right = right(index);
		}
	}
	public void insert(int n)
	{
		// array[currentIndex++] = n ;
		array[currentIndex] = n ;
		// System.out.println(array[currentIndex]);
		// System.out.println(currentIndex);
		
		up(currentIndex);
		currentIndex++;
	}	
	public int []getInner(){
		return array;
	}
	public int getCurrentIndex(){
		return currentIndex;
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