package lockingfingertree;

public abstract class FingerTree<T> {

	public abstract int size();
	
	public abstract FingerTree<T> insertLast(T a);
	
	public abstract FingerTree<T> insertFirst(T a);
	
	public abstract T first();
	
	public abstract T last();
	
	public abstract View<T> removeFirst();
	
	public abstract View<T> removeLast();
	
	public abstract String toString();
}
