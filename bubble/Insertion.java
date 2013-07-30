public class Insertion{
	public Insertion(){}	
	public void run(int[] src){
		int N = src.length;
		for(int i=1;i<N;i++){
			int j = i;
			int v = src[i];
			while( j>0  && v < src[j-1]){
				src[j] = src[j-1];
				j--;				
			}
			src[j] = v ;
		}
		return ;
	}
}