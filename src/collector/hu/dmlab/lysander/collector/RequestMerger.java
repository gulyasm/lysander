package hu.dmlab.lysander.collector;

import hu.dmlab.lysander.monitor.Event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RequestMerger {

	private HashMap<Long, Request> requests;
	private int expected;

	public RequestMerger(int expected) {
		this.expected = expected;
		requests = new HashMap<>();
	}

	public List<Request> merge(List<Event> events) {
		ArrayList<Request> completed = new ArrayList<>();
		for (Event event : events) {
			Request request = getRequest(event.id);
			request.addEvent(event);
			if (request.isComplete()) {
				completed.add(request);
			}
		}
		return completed;
	}

	private Request getRequest(long id) {
		Request request = null;
		if (requests.containsKey(id)) {
			request = requests.get(id);
		} else {
			request = new Request(id, expected);
			requests.put(id, request);
		}
		return request;
	}

}
