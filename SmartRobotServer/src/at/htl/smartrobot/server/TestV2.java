package at.htl.smartrobot.server;

public class TestV2 {

	
	private int runs = 5000;
	private long[] offset_Timer = new long[runs];
	long systemTimer_before;
	long systemTimer_after;
	long nanoTimer_before;
	long nanoTimer_after;
	
	
	
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
