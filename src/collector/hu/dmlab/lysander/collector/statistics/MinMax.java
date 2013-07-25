package hu.dmlab.lysander.collector.statistics;

import hu.dmlab.lysander.collector.Request;
import hu.dmlab.lysander.collector.StatisticianBase;

import java.util.List;

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

	/**
	 * @return result[0] is the min, result[1] is the max. Length of the array
	 *         is 2.
	 */
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
