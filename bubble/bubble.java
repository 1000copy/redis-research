public class Bubble{ 
 public int[] run(int[] src) {  
    int N = src.length ;     
    for(int i=0;i<N;i++){
	    src = bubble_big(src,N-i);
  	}
    return src;
  }
  int[] bubble_big(int[] src,int len){     
  	 for (int i = 1 ;i<len;i++){
  	 	if (src[i-1] > src[i]){
  	 		int v = src[i];
  	 		src[i] = src[i-1];
  	 		src[i-1] = v ;
  	 	}
  	 }	
  	 return src ;
  }  
}