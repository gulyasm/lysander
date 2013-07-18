package hu.dmlab.lysander.collector.save;

import hu.dmlab.lysander.collector.Request;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public interface SaveService {

	public void save(Collection<Request> data, String path) throws IOException;

	public ArrayList<Request> restore(String path);

}
