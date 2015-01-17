package at.htl.smartrobot.server.utils;

import java.nio.ByteBuffer;

public class ByteUtils {
	
	private static ByteBuffer buffer;
	
	public static byte[] longToBytes(long x){
		buffer=ByteBuffer.allocate(Long.SIZE / 8);
		buffer.putLong(x);
		byte[] result = buffer.array();
		buffer = null;
		return result;
	}
	
	public static long bytesToLong(byte[] bytes) {
		buffer=ByteBuffer.allocate(Long.SIZE / 8);
		buffer.put(bytes, 0, bytes.length);
		buffer.flip();
		long result = buffer.getLong();
		buffer = null;
		return result;
	}
}
