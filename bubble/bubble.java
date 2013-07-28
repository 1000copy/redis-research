public class bubble{ 
 public static void main(String[] args) {  
    int[] src = {3,2,5,4,1};
    print_array(src);
    int N = src.length ;
    for(int i=0;i<N;i++){
	    src = bubble_big(src,N-i);
	    System.out.println(src[N-i-1]);
	}
    print_array(src);
  }
  static int[] bubble_big(int[] src,int len){
  	 for (int i = 1 ;i<len;i++){
  	 	if (src[i-1] > src[i]){
  	 		int v = src[i];
  	 		src[i] = src[i-1];
  	 		src[i-1] = v ;
  	 	}
  	 }	
  	 return src ;
  }
  static void print_array(int[] a){
  	for (int i=0;i<a.length;i++)
  		System.out.print(String.format("%d ",a[i]));
  	System.out.println();
  }
}