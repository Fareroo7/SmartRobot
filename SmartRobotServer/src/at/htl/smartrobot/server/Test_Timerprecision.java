package at.htl.smartrobot.server;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/*
 * 
 * 
 * !!!Zu ungenau!!!
 * 
 * 
 */


public class Test_Timerprecision {

	public static BufferedWriter bw;
	public static ArrayList<Long> values = new ArrayList<Long>();
	public static long lastTime;
	public static long currentTime;
	public static int count = 0;
	public static int runs = 1000;

	public static void main(String[] args) {

		try {
			bw = new BufferedWriter(new FileWriter(new File("./timerprecision.csv")));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		class TestTask extends TimerTask {

			@Override
			public void run() {
				currentTime = System.nanoTime();
				values.add(currentTime - lastTime);
				lastTime = currentTime;
				count++;

//				System.out.println(count);

				if (count == runs) {

					System.out.println("start printing");

					for (long difference : values) {
						try {
							bw.write(Long.toString(difference));
							System.out.println(difference);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
					try {
						bw.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					this.cancel();
				}

			}
		}

		try {

			bw.write("difference");
			bw.newLine();
			Timer t = new Timer();

			lastTime = System.nanoTime();
			t.schedule(new TestTask(), 10, 10);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
