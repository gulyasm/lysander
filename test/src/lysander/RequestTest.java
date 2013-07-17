package lysander;

import static org.junit.Assert.*;
import hu.dmlab.lysander.collector.Request;
import hu.dmlab.lysander.monitor.Event;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RequestTest {

	Request request;
	Event goodEvent1, evilEvent, goodEvent3, goodEvent2;
	long event1Ts, event2Ts, event3Ts;
	long id = 32432;
	long badId = 5643;

	@Before
	public void setUp() throws Exception {
		event1Ts = 100;
		event2Ts = 200;
		event3Ts = 300;
		request = new Request(id,3);
		goodEvent1 = new Event(id, event1Ts, "1");
		goodEvent2 = new Event(id, event2Ts, "2");
		goodEvent3 = new Event(id, event3Ts, "3");
		evilEvent = new Event(badId, event1Ts, "3");
		request.addEvent(goodEvent1);
		request.addEvent(goodEvent2);
	}

	@After
	public void tearDown() throws Exception {
		event3Ts = event2Ts = event1Ts = 0;
		goodEvent1 = goodEvent2 = goodEvent3 = evilEvent = null;
		request = null;
	}

	@Test(expected = IllegalArgumentException.class)
	public void testIllegalAddEvent() {
		request.addEvent(evilEvent);
	}

	@Test
	public void testAddEvent() {
		request = new Request(id);
		request.addEvent(goodEvent2);
		assertEquals(goodEvent2.timestamp, request.getEnd());
		assertEquals(goodEvent2.timestamp, request.getStart());
		request.addEvent(goodEvent1);
		assertEquals(goodEvent2.timestamp, request.getEnd());
		assertEquals(goodEvent1.timestamp, request.getStart());
		request.addEvent(goodEvent3);
		assertEquals(goodEvent3.timestamp, request.getEnd());
		assertEquals(goodEvent1.timestamp, request.getStart());
	}

	@Test
	public void testIsComplete() {
		assertEquals(false, request.isComplete());
		request.addEvent(goodEvent3);
		assertEquals(true, request.isComplete());
	}

	@Test
	public void testDelta() {
		request.addEvent(goodEvent3);
		assertEquals(100, request.delta(goodEvent1.type, goodEvent2.type));
		assertEquals(100, request.delta(goodEvent2.type, goodEvent3.type));
		assertEquals(200, request.delta(goodEvent1.type, goodEvent3.type));
		assertEquals(-200, request.delta(goodEvent3.type, goodEvent1.type));
		assertEquals(-100, request.delta(goodEvent3.type, goodEvent2.type));
		assertEquals(0, request.delta(goodEvent3.type, goodEvent3.type));
	}
	
	@Test
	public void testDuration(){
		request.addEvent(goodEvent3);
		assertEquals(200, request.duration());
	}

}
