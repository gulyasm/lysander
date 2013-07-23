package hu.dmlab.lysander.monitor;

public abstract class EventMonitorBase implements EventMonitor {
	private long currentMaxId = 0;
	private String prefix;

	public void setCurrentMaxId(long currentMaxId) {
		this.currentMaxId = currentMaxId;
	}

	public long popId() {
		return ++currentMaxId;
	}

	public long peakId() {
		return currentMaxId;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
}
