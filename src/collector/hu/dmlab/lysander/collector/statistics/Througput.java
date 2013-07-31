package hu.dmlab.lysander.collector.statistics;

import hu.dmlab.lysander.collector.Request;
import hu.dmlab.lysander.collector.StatisticianBase;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.google.common.primitives.Longs;

public class Througput extends StatisticianBase<Request, int[]> {

	private int[] throughputs = new int[512];
	private long nextSeconds = 0;
	private int currentIndex = 0;

	@Override
	public boolean doMath() {
		super.doMath();
		return true;
	}

	@Override
	public void setData(List<Request> data) {
		super.setData(data);
		Collections.sort(data, new Comparator<Request>() {

			@Override
			public int compare(Request o1, Request o2) {
				return Longs.compare(o1.getEnd(), o2.getEnd());
			}
		});
	}

	@Override
	public int[] getResult() {
		return Arrays.copyOf(throughputs, currentIndex + 1);
	}

	@Override
	protected void processItem(Request request) {
		ensureArrayLength();
		long current = request.getEnd();
		if (nextSeconds == 0) {
			nextSeconds = current + 1000;
		}
		if (current > nextSeconds) {
			currentIndex++;
			nextSeconds += 1000;
		}
		throughputs[currentIndex]++;
	}

	private void ensureArrayLength() {
		if (currentIndex == throughputs.length - 1) {
			throughputs = Arrays.copyOf(throughputs, throughputs.length << 1);

		}
	}
}
