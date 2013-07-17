package hu.dmlab.lysander.monitor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileBackedEventMonitor extends EventMonitorBase {

	private FileOutputStream fileStream;

	public FileBackedEventMonitor(File file) throws IOException {
		if (file.exists() && file.isDirectory()) {
			throw new IllegalArgumentException();
		}
		if (!file.exists()) {
			file.createNewFile();
		}
		fileStream = new FileOutputStream(file);
	}

	public void flush() throws IOException {
		fileStream.flush();
	}

	public void close() {
		try {
			fileStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public long report(Event event) {
		try {
			event.serialize(fileStream);
		} catch (IOException e) {
			return -1;
		}
		return event.id;
	}

}
