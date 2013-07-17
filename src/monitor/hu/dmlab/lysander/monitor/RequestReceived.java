package hu.dmlab.lysander.monitor;

public class RequestReceived extends Event {

	public static final String TYPE = "rr";
	
	public RequestReceived(long id, long timestamp) {
		super(id, timestamp, TYPE);
	}

}
