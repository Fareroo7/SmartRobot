package at.htl.smartrobot.server;

import java.nio.ByteBuffer;

public class ByteUtils {
	
	private static ByteBuffer buffer = ByteBuffer.allocate(Long.SIZE / 8);
	
	public static byte[] longToBytes(long x){
		buffer.putLong(x);
		return buffer.array();
	}
	
	public static long bytesToLong(byte[] bytes) {
		buffer.put(bytes, 0, bytes.length);
		buffer.flip();
		return buffer.getLong();
	}
}
