package hu.dmlab.lysander.monitor;

public class RequestFinished extends Event {

	public static final String TYPE = "rf";
	
	public RequestFinished(long id, long timestamp) {
		super(id, timestamp, TYPE);
	}

}
