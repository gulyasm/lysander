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
	private final String prefix = "prefix";

	@Test
	public void testSetCurrentMaxId() {
		setRandomId();
		assertEquals(id, monitor.peakId());
		assertEquals(id + 1, monitor.popId());
	}

	private void setRandomId() {
		id = random.nextLong();
		monitor.setCurrentMaxId(id);
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
		random = new Random(System.currentTimeMillis());
		file = folder.newFile();
		monitor = new FileBackedEventMonitor(file);
		monitor.setCurrentMaxId(id);
		monitor.setPrefix(prefix);
	}

	@Test
	public void testConstructor() {
		assertEquals(prefix, monitor.getPrefix());
	}

	@Test
	public void testReport() throws IOException {
		final Event event = new TestEvent();
		setRandomId();
		monitor.report(event);
		monitor.close();
		final BufferedReader br = new BufferedReader(new FileReader(file));
		final String to = TestEvent.id + "," + Long.toString(TestEvent.timestamp) + "," + TestEvent.type;
		assertEquals(to, br.readLine());
		br.close();
	}

	@Test
	public void testConst() {
		assertEquals(0, monitor.peakId());
		assertEquals(1, monitor.popId());
	}

}
