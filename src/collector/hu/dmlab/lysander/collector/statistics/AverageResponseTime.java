package hu.dmlab.lysander.collector.statistics;

import hu.dmlab.lysander.collector.Request;
import hu.dmlab.lysander.collector.Statistician;

import java.util.List;

import com.google.common.base.Preconditions;

public class AverageResponseTime implements Statistician<Request, Double> {

	private long sum = 0;
	private int size = 0;
	private List<Request> data;

	@Override
	public void setData(List<Request> data) {
		this.data = data;

	}

	@Override
	public boolean doMath() {
		Preconditions.checkState(sum == 0);
		Preconditions.checkState(size == 0);
		Preconditions.checkState(data != null);
		for (Request request : data) {
			long duration = request.duration();
			sum += duration;
			size++;
		}
		data = null;
		return true;
	}

	@Override
	public Double getResult() {
		return sum / (double) size;
	}

}
