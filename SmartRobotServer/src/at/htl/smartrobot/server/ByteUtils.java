package at.htl.smartrobot.server;

import java.nio.ByteBuffer;

public class ByteUtils {
	
	private static ByteBuffer buffer = ByteBuffer.allocate(Long.SIZE / 8);
	
	public static byte[] longToBytes(long x){
		buffer.putLong(x);
		byte[] result = buffer.array();
		buffer = null;
		return result;
	}
	
	public static long bytesToLong(byte[] bytes) {
		buffer.put(bytes, 0, bytes.length);
		buffer.flip();
		long result = buffer.getLong();
		buffer = null;
		return result;
	}
}
