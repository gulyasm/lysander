package hu.dmlab.lysander.collector;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import hu.dmlab.lysander.monitor.RequestReceived;

import org.junit.Test;

public class RequestReceivedTest {

	@Test
	public void testRequestReceived() {
		int id = 324;
		int timestamp = 43243;
		RequestReceived s = new RequestReceived(id, timestamp);
		assertEquals(id, s.id);
		assertEquals(timestamp, s.timestamp);
		assertEquals(RequestReceived.TYPE, s.type);
	}

	@Test
	public void testSerialize() throws IOException {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		int id = 324;
		int timestamp = 43243;
		RequestReceived s = new RequestReceived(id, timestamp);
		s.serialize(stream);
		final String expected = "324,43243," + RequestReceived.TYPE + "\n\r";
		assertEquals(expected, stream.toString("utf-8"));
	}

}
