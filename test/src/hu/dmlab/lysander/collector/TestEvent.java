package hu.dmlab.lysander.collector;

import static org.junit.Assert.*;

import org.junit.Test;

import hu.dmlab.lysander.monitor.Event;

public class TestEvent extends Event {

	public static String id = "3242423";
	public static long timestamp = 1234;
	public static String type = "mate";

	public TestEvent() {
		super(id, timestamp, type);
	}
	
}
