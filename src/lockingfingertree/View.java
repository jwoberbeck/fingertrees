package lockingfingertree;

public class View<T> {
	
	private T _elem;
	private FingerTree<T> _ft;
	
	public View(T elem, FingerTree<T> ft) {
		_elem = elem;
		_ft = ft;
	}
	
	public T element() {
		return _elem;
	}
	
	public FingerTree<T> tree() {
		return _ft;
	}

}
