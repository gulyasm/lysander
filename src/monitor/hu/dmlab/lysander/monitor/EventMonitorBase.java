package hu.dmlab.lysander.monitor;


public abstract class EventMonitorBase implements EventMonitor {
	private long currentMaxId = 0;

	public void setCurrentMaxId(long currentMaxId) {
		this.currentMaxId = currentMaxId;
	}

	public long popId() {
		return ++currentMaxId;
	}

	public long peakId() {
		return currentMaxId;
	}

	public Event forType(String type) {
		return new Event(popId(), System.currentTimeMillis(),type);

	}

}
