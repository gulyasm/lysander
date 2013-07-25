package hu.dmlab.lysander.collector.save;

import hu.dmlab.lysander.collector.Request;

import java.io.IOException;
import java.util.ArrayList;

public interface SaveService {

	public void save(ArrayList<Request> data, String path) throws IOException;

	public ArrayList<Request> restore(String path);

}
