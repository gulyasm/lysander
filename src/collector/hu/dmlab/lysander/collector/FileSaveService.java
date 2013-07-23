package hu.dmlab.lysander.collector;

import hu.dmlab.lysander.collector.save.SaveService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileSaveService implements SaveService {

	@Override
	public void save(ArrayList<Request> data, String path) throws IOException {
		File file = new File(path);
		if (file.exists() && file.isDirectory()) {
			return;
		}
		if (!file.exists()) {
			file.createNewFile();
		}
		try (ObjectOutputStream stream = new ObjectOutputStream(
				new FileOutputStream(file))) {
			stream.writeObject(data);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Request> restore(String path) {

		try {
			ArrayList<Request> list = null;
			File file = new File(path);
			try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(
					file))) {
				list = ((ArrayList<Request>) stream.readObject());
			}
			return list;
		} catch (ClassNotFoundException | IOException e) {
			return null;
		}
	}

}
