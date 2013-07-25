package hu.dmlab.lysander.collector;

import java.util.List;

import com.google.common.base.Preconditions;

public abstract class StatisticianBase<T, V> implements Statistician<T, V> {

	private List<T> data;

	@Override
	public void setData(List<T> data) {
		this.data = data;
	}

	@Override
	public boolean doMath() {
		Preconditions.checkState(data != null, "Data is not set, or called twice.");
		for (T t : data) {
			processItem(t);
		}
		data = null;
		return true;
	}

	protected abstract void processItem(T t);

}
