package hu.dmlab.lysander.collector;

import static org.junit.Assert.assertEquals;
import hu.dmlab.lysander.monitor.Event;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RequestMergerTest {

	private RequestMerger merger;
	Event event1, event2, event3;

	@Before
	public void setUp() throws Exception {
		merger = new RequestMerger(2);
		event1 = new Event("23", 100, "1");
		event2 = new Event(event1.id, 200, "2");
		event3 = new Event("34", 200, "2");
	}

	@After
	public void tearDown() throws Exception {
		merger = null;
		event1 = event2 = null;
	}

	@Test
	public void oneEvent() {
		List<Request> list = merger.merge(Arrays.asList(event1, event2));
		assertEquals(1, list.size());
	}

	@Test
	public void noEvent() {
		List<Request> list = merger.merge(Arrays.asList(event1, event3));
		assertEquals(0, list.size());
	}

	@Test
	public void noEvent2() {
		merger = new RequestMerger(3);
		List<Request> list = merger.merge(Arrays.asList(event1, event2));
		assertEquals(0, list.size());
	}

}
