package hu.dmlab.lysander.collector;

import static org.junit.Assert.assertEquals;
import hu.dmlab.lysander.monitor.Event;
import hu.dmlab.lysander.monitor.FileBackedEventMonitor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class FileBackedEventMonitorTest {

	@Rule
	public TemporaryFolder folder = new TemporaryFolder();
	private FileBackedEventMonitor monitor;
	private File file;
	private Random random;
	private long id;

	@Test
	public void testSetCurrentMaxId() {
		assertEquals(id, monitor.peakId());
		assertEquals(id + 1, monitor.popId());
	}

	@After
	public void tearDown() {
		id = -1;
		monitor.close();
		file.delete();
		file = null;
		monitor = null;
		random = null;
	}

	@Before
	public void setup() throws IOException {
		file = folder.newFile();
		monitor = new FileBackedEventMonitor(file);
		random = new Random(System.currentTimeMillis());
		monitor.setCurrentMaxId(id);
	}

	@Test
	public void testId() {
		id = random.nextLong();
	}
	
	@Test
	@Ignore
	public void testReport() throws IOException {
		Event event = new TestEvent();
		monitor.report(event);
		monitor.close();
		BufferedReader br = new BufferedReader(new FileReader(file));
		String expected = TestEvent.id + "," + TestEvent.timestamp + "," + TestEvent.type
				+ "\n\r";
		assertEquals(expected, br.readLine());
		br.close();
	}

	@Test
	public void testConst() {
		assertEquals(0, monitor.peakId());
		assertEquals(1, monitor.popId());
	}

}
