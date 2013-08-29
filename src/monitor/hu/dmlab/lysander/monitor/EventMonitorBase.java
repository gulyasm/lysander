package hu.dmlab.lysander.monitor;

public abstract class EventMonitorBase implements EventMonitor {
	private long currentMaxId = 0;
	private String prefix;

	/**
	 * The monitor can be set to this value, and id generation will go from this
	 * value. This value is available via {@link #peakId()}
	 * 
	 * @param currentMaxId
	 */
	public void setCurrentMaxId(long currentMaxId) {
		this.currentMaxId = currentMaxId;
	}

	/**
	 * Returns a new id.
	 * 
	 * @return a new id, ready to use.
	 * @throws IllegalStateException
	 *             if there is no more available id.
	 */
	public long popId() {
		if (currentMaxId == Long.MAX_VALUE) {
			throw new IllegalStateException("Id reached max value. Current value: " + currentMaxId);
		}
		return ++currentMaxId;
	}

	/**
	 * Returns a new id.
	 * 
	 * @return
	 */
	public long peakId() {
		return currentMaxId;
	}

	/**
	 * Returns the prefix.
	 * 
	 * @return the prefix
	 */
	public String getPrefix() {
		return prefix;
	}

	/**
	 * Guarantees that the event monitors id is unique across multiple event
	 * monitors
	 * 
	 * @param prefix
	 */
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
}
