package at.htl.smartrobot.server;

public class TestV2 {

	public static int fib(int n){
		int pre = -1;
		int res = 1;
		
		for(int i = 0; i <= n; i++){
			int sum = res + pre;
			pre = res;
			res = sum;
		}
		
		return res;
		
	}
	
}
