package hu.dmlab.lysander.collector.statistics;

import hu.dmlab.lysander.collector.Request;
import hu.dmlab.lysander.collector.StatisticianBase;

import com.google.common.base.Preconditions;

public class AverageResponseTime extends StatisticianBase<Request, Double> {

	private long sum = 0;
	private int size = 0;

	@Override
	public boolean doMath() {
		Preconditions.checkState(sum == 0);
		Preconditions.checkState(size == 0);
		super.doMath();
		return true;
	}

	@Override
	public Double getResult() {
		return sum / (double) size;
	}

	@Override
	protected void processItem(Request request) {
		long duration = request.duration();
		sum += duration;
		size++;
	}

}
