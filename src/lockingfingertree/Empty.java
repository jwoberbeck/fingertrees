package lockingfingertree;

public class Empty<T> extends FingerTree<T> {
	
	public Empty() {
		super();
	}
	
	public int size() {
		return 0;
	}

	@Override
	public FingerTree<T> insertLast(T a) {
		return new Single<T>(a);
	}

	@Override
	public FingerTree<T> insertFirst(T a) {
		return new Single<T>(a);
	}

	@Override
	public T first() {
		return null;
	}

	@Override
	public T last() {
		return null;
	}

	@Override
	public View<T> removeFirst() {
		return new View<T>(null, new Empty<T>());
	}

	@Override
	public View<T> removeLast() {
		return new View<T>(null, new Empty<T>());
	}
	
	public String toString() {
		return " EMPTY_TREE ";
	}

}
