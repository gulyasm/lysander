package hu.dmlab.lysander.monitor;

import java.io.IOException;
import java.io.OutputStream;

public class Event {

	private static final byte[] newline = new byte[] { 10, 13 };
	public final long id;
	public final String type;
	public final long timestamp;

	public Event(long id, long timestamp, String type) {
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
		String type = splitted[2];
		long id = Long.parseLong(splitted[0]);
		long timestamp = Long.parseLong(splitted[1]);
		return new Event(id, timestamp, type);
	}

	public void serialize(OutputStream output) throws IOException {
		output.write(Long.toString(id).getBytes());
		output.write(44); // ,
		output.write(Long.toString(timestamp).getBytes());
		output.write(44);
		output.write(type.getBytes("utf-8"));
		output.write(newline);
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", type=" + type + ", timestamp=" + timestamp + "]";
	}

}
