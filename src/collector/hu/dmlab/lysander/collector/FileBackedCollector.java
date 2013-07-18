package hu.dmlab.lysander.collector;

import hu.dmlab.lysander.collector.save.SaveService;
import hu.dmlab.lysander.monitor.Event;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileBackedCollector {

	private static String path = "E:/TEMP/dave";

	public static void main(String[] args) throws IOException {
		if (args.length < 1) {
			return;
		}
		List<Event> events = new ArrayList<>();
		for (int i = 0; i < args.length; i++) {
			File file = new File(args[i]);
			if (!file.exists() || !file.isFile()) {
				return;
			}
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = null;
			while ((line = reader.readLine()) != null) {
				Event event = Event.deserialize(line);
				if (event != null) {
					events.add(event);
				}
			}
			reader.close();
		}

		RequestMerger merger = new RequestMerger(2);
		List<Request> requests = merger.merge(events);
		SaveService service = new FileSaveService();
		service.save(requests, path);
	}

}
