package hu.dmlab.lysander.collector;

import java.util.List;

public interface Statistician<T, V> {

	public void setData(List<T> data);

	public boolean doMath();

	public V getResult();

}
