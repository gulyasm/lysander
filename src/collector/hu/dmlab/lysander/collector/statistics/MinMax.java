package hu.dmlab.lysander.collector.statistics;

import java.util.List;

import com.google.common.base.Preconditions;

import hu.dmlab.lysander.collector.Request;
import hu.dmlab.lysander.collector.Statistician;

public class MinMax implements Statistician<Request, String> {
	private long min = 0;
	private long max = 0;
	private List<Request> data;

	@Override
	public void setData(List<Request> data) {
		this.data = data;
	}

	@Override
	public boolean doMath() {
		Preconditions.checkState(min == 0);
		Preconditions.checkState(max == 0);
		Preconditions.checkState(data != null);
		max = Long.MIN_VALUE;
		min = Long.MAX_VALUE;
		for (Request request : data) {
			long duration = request.duration();
			max = Math.max(max, duration);
			min = Math.min(min, duration);
		}
		data = null;
		return true;
	}

	@Override
	public String getResult() {
		return min + ", " + max;
	}

}
