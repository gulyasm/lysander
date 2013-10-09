package hu.dmlab.lysander.monitor;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import com.google.common.primitives.Longs;

public class Event implements Comparable<Event> {

	// Not final due to exception handling
	private static byte[] newline;
	public final String id;
	public final String type;
	public final long timestamp;
	public final int payload1;
	public final int payload2;

	static {
		try {
			newline = System.getProperty("line.separator").getBytes("utf-8");
		} catch (UnsupportedEncodingException e) {
			// Fall back to unix line end
			newline = new byte[] { 10 };
		}
	}

	public Event(String id, long timestamp, String type) {
		this(id, timestamp, type, -1, -1);
	}

	public Event(String id, long timestamp, String type, int payload1, int payload2) {
		super();
		this.id = id;
		this.timestamp = timestamp;
		this.type = type;
		this.payload1 = payload1;
		this.payload2 = payload2;
	}

	public static Event deserialize(String string) throws IOException {
		String[] splitted = string.split(",", 5);
		if (splitted.length < 3) {
			return null;
		}
		String type = splitted[2].trim();
		String id = splitted[0].trim();
		long timestamp = Long.parseLong(splitted[1]);
		int payload1 = splitted.length >= 4 ? Integer.parseInt(splitted[3]) : 0;
		int payload2 = splitted.length >= 5 ? Integer.parseInt(splitted[4]) : 0;
		return new Event(id, timestamp, type, payload1, payload2);
	}

	public void serialize(OutputStream output) throws IOException {
		output.write(id.getBytes("utf-8"));
		output.write(44); // byte of ',' character
		output.write(Long.toString(timestamp).getBytes("utf-8"));
		output.write(44);
		output.write(type.getBytes("utf-8"));
		output.write(44);
		output.write(Integer.toString(payload1).getBytes("utf-8"));
		output.write(44);
		output.write(Integer.toString(payload2).getBytes("utf-8"));
		output.write(newline);
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", type=" + type + ", timestamp=" + timestamp + "]";
	}

	@Override
	public int compareTo(Event arg0) {
		return Longs.compare(timestamp, arg0.timestamp);
	}

}
