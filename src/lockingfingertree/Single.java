package lockingfingertree;

public class Single<T> extends FingerTree<T> {
	
	private T _val;
	
	public Single(T a) {
		super();
		_val = a;
	}

	public int size() {
		return 1;
	}

	@Override
	public FingerTree<T> insertLast(T a) {
		return new Deep<T>(new Digit<T>(_val), new Empty<Node3<T>>(), new BackDigit<T>(a));
	}

	@Override
	public FingerTree<T> insertFirst(T a) {
		return new Deep<T>(new Digit<T>(a), new Empty<Node3<T>>(), new BackDigit<T>(_val));
	}

	@Override
	public T first() {
		return _val;
	}

	@Override
	public T last() {
		return _val;
	}

	@Override
	public View<T> removeFirst() {
		return new View<T>(_val, new Empty<T>());
	}

	@Override
	public View<T> removeLast() {
		return new View<T>(_val, new Empty<T>());
	}
	
	public String toString() {
		return " " + _val.toString() + " ";
	}

}
