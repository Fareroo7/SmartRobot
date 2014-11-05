package at.htl.smartrobot.server;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;

public class TestV2 {

	private static int runs = 5000;
	private static long[] offsets = new long[runs];
	private static long systemTimerAfter, systemTimerBefore, nanoTimerAfter, nanoTimerBefore, offsetSystemTime, offsetNanoTime;
	
	public static void main(String[] args) {
	
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File("./offsetTimers.csv")));
		
			for(int i = 0; i < 5000; i++){
				systemTimerBefore = System.currentTimeMillis();
				nanoTimerBefore = System.nanoTime();
				fibR(30);
				systemTimerAfter = System.currentTimeMillis();
				nanoTimerAfter = System.nanoTime();
	
				offsetSystemTime = systemTimerBefore - systemTimerAfter;
				offsetNanoTime = nanoTimerBefore - nanoTimerAfter;
				
				offsets[i] = offsetNanoTime - (offsetSystemTime * 1000000);
				System.out.println(i);
			}
			
			for(long offset : offsets){
				bw.write(Long.toString(offset) + ";");
				bw.newLine();
			}
			
			bw.close();
			
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static long fibR(long n){
		return n <= 0 ? 0 : n == 1 ? 1 : fibR(n - 1) + fibR(n - 2);
	}
	
	public static long fibI(long n){
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
