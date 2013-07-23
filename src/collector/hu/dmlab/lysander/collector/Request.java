package hu.dmlab.lysander.collector;

import hu.dmlab.lysander.monitor.Event;

import java.io.Serializable;
import java.util.HashMap;

import com.google.common.base.Preconditions;

public class Request implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private long endTimestamp = 0;
	private long startTimestamp = 0;
	private HashMap<String, Long> eventMap;
	private int expectedEvents;

	public Request(String id, int expectedEvents) {
		this.id = id;
		this.expectedEvents = expectedEvents;
		eventMap = new HashMap<>();
	}

	public Request(String id) {
		this(id, 2);
	}

	public void addEvent(Event event) {
		Preconditions.checkArgument(this.id.equals(event.id));
		eventMap.put(event.type, event.timestamp);
		final long timestamp = event.timestamp;
		if (startTimestamp > timestamp || startTimestamp == 0) {
			startTimestamp = timestamp;
		}
		if (endTimestamp < timestamp || endTimestamp == 0) {
			endTimestamp = timestamp;
		}
	}

	public boolean isComplete() {
		return eventMap.size() == expectedEvents;
	}

	public long delta(String from, String to) {
		long fromTimeStamp = eventMap.get(from);
		long toTimeStamp = eventMap.get(to);
		Preconditions.checkArgument(fromTimeStamp != 0);
		Preconditions.checkArgument(toTimeStamp != 0);
		return toTimeStamp - fromTimeStamp;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Request)) return false;
		return id == ((Request) obj).id;
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}

	public long getEnd() {
		return endTimestamp;
	}

	public long getStart() {
		return startTimestamp;
	}

	@Override
	public String toString() {
		return "Request [id=" + id + ", endTimestamp=" + endTimestamp
				+ ", startTimestamp=" + startTimestamp + ", events=" + eventMap.size()
				+ "]";
	}

	public long duration() {
		return endTimestamp - startTimestamp;
	}

}
