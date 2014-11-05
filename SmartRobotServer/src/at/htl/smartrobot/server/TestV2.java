package at.htl.smartrobot.server;

public class TestV2 {

	
	public static void main(String[] args) {
		System.out.println(fib(700));
	}
	
	public static long fib(long n){
		long pre = -1;
		long res = 1;
		
		for(long i = 0; i <= n; i++){
			long sum = res + pre;
			pre = res;
			res = sum;			
		}
		
		return res;
		
	}
	
}
