package lysander;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import hu.dmlab.lysander.monitor.RequestFinished;

import org.junit.Test;

public class RequestFinishedTest {

	@Test
	public void testRequestFinished() {
		int id = 324;
		int timestamp = 43243;
		RequestFinished s = new RequestFinished(id, timestamp);
		assertEquals(id, s.id);
		assertEquals(timestamp, s.timestamp);
		assertEquals(RequestFinished.TYPE, s.type);
	}

	@Test
	public void testSerialize() throws IOException {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		int id = 324;
		int timestamp = 43243;
		RequestFinished s = new RequestFinished(id, timestamp);
		s.serialize(stream);
		final String expected = "324,43243," + RequestFinished.TYPE + "\n\r";
		assertEquals(expected, stream.toString("utf-8"));
	}

}
