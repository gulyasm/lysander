package hu.dmlab.lysander.collector;

import hu.dmlab.lysander.collector.save.SaveService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;

public class FileSaveService implements SaveService {

	@Override
	public void save(Collection<Request> data, String path) throws IOException {
		File file = new File(path);
		if (file.exists() && file.isDirectory()) {
			return;
		}
		if (!file.exists()) {
			file.createNewFile();
		}
		try (ObjectOutputStream stream = new ObjectOutputStream(
				new FileOutputStream(file))) {
			for (Request request : data) {
				stream.writeObject(request);
			}
		}
	}

	@Override
	public ArrayList<Request> restore(String path) {

		try {
			ArrayList<Request> list = new ArrayList<>();
			File file = new File(path);
			try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(
					file))) {
				while (stream.available() > 0) {
					list.add((Request) stream.readObject());
				}
			}
			return list;
		} catch (ClassNotFoundException | IOException e) {
			return null;
		}
	}

}
