package hu.dmlab.lysander.monitor;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

public class Event {

	// Not final due to exception handling
	private static byte[] newline;
	public final String id;
	public final String type;
	public final long timestamp;

	static {
		try {
			newline = System.getProperty("line.separator").getBytes("utf-8");
		} catch (UnsupportedEncodingException e) {
			// Fall back to unix line end
			newline = new byte[] { 10 };
		}
	}

	public Event(String id, long timestamp, String type) {
		super();
		this.id = id;
		this.timestamp = timestamp;
		this.type = type;
	}

	public static Event deserialize(String string) throws IOException {
		String[] splitted = string.split(",", 3);
		if (splitted.length < 3) {
			return null;
		}
		String type = splitted[2].trim();
		String id = splitted[0].trim();
		long timestamp = Long.parseLong(splitted[1]);
		return new Event(id, timestamp, type);
	}

	public void serialize(OutputStream output) throws IOException {
		output.write(id.getBytes("utf-8"));
		output.write(44); // byte of ',' character
		output.write(Long.toString(timestamp).getBytes("utf-8"));
		output.write(44);
		output.write(type.getBytes("utf-8"));
		output.write(newline);
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", type=" + type + ", timestamp=" + timestamp + "]";
	}

}
