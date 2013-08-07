package hu.dmlab.lysander.collector;

import hu.dmlab.lysander.monitor.Event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class RequestMerger {

	private HashMap<String, Request> requests;
	private HashSet<String> completedRequests;
	private int expected;

	public RequestMerger(int expected) {
		this.expected = expected;
		requests = new HashMap<>();
		completedRequests = new HashSet<>();
	}

	public ArrayList<Request> merge(List<Event> events) {
		ArrayList<Request> completed = new ArrayList<>();
		for (Event event : events) {
			if (completedRequests.contains(event.id)) {
				continue;
			}
			Request request = getRequest(event.id);
			request.addEvent(event);
			if (request.isComplete()) {
				completed.add(request);
				completedRequests.add(event.id);
			}
		}
		return completed;
	}

	private Request getRequest(String id) {
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
