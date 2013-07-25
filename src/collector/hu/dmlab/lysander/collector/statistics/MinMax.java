package hu.dmlab.lysander.collector.statistics;

import hu.dmlab.lysander.collector.Request;
import hu.dmlab.lysander.collector.StatisticianBase;

public class MinMax extends StatisticianBase<Request, long[]> {
	private long min = 0;
	private long max = 0;

	@Override
	public boolean doMath() {
		max = Long.MIN_VALUE;
		min = Long.MAX_VALUE;
		super.doMath();
		return true;
	}

	@Override
	public long[] getResult() {
		return new long[] { min, max };
	}

	@Override
	protected void processItem(Request request) {
		long duration = request.duration();
		max = Math.max(max, duration);
		min = Math.min(min, duration);
	}

}
