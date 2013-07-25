package hu.dmlab.lysander.collector;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import hu.dmlab.lysander.monitor.Event;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.junit.Test;

public class EventTest {

	@Test
	public void testConstructor() {
		final String type = "aaa";
		final String id = "3243";
		final long timestamp = 435343;
		final Event event = new Event(id, timestamp, type);
		assertEquals(event.id, id);
		assertEquals(event.timestamp, timestamp);
		assertEquals(event.type, type);
	}

	@Test
	public void testSerialize() throws IOException {
		final ByteArrayOutputStream stream = new ByteArrayOutputStream();
		final String id = "324";
		final int timestamp = 43243;
		final String type = "aaa";
		final Event s = new Event(id, timestamp, type);
		s.serialize(stream);
		final String to = id + "," + Long.toString(timestamp) + "," + type + System.getProperty("line.separator");
		assertArrayEquals(to.getBytes("utf-8"), stream.toByteArray());
	}

	@Test
	public void testDeserialize() throws IOException {
		final String id = "324";
		final int timestamp = 43243;
		final String type = "aaa";
		final String from = id + "," + Long.toString(timestamp) + "," + type + System.getProperty("line.separator");
		Event event = Event.deserialize(from);
		assertEquals(event.id, id);
		assertEquals(event.timestamp, timestamp);
		assertEquals(event.type, type);
	}

}
