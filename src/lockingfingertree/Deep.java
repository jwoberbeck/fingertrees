package lockingfingertree;

public class Deep<T> extends FingerTree<T> {
	
	private Digit<T> _pr;
	private BackDigit<T> _sf;
	private FingerTree<Node3<T>> _mid;
	
	public Deep(Digit<T> pr, FingerTree<Node3<T>> mid, BackDigit<T> sf) {
		super();
		_pr = pr;
		_mid = mid;
		_sf = sf;
	}
	
	public int size() {
		return _pr.size() + _mid.size() + _sf.size();
	}

	@Override
	public FingerTree<T> insertLast(T a) {
		Node3<T> out = _sf.insert(a);
		if(out != null) {
			_mid = _mid.insertLast(out);
		}
		return this;
	}

	@Override
	public FingerTree<T> insertFirst(T a) {
		Node3<T> out = _pr.insert(a);
		if(out != null) {
			_mid = _mid.insertFirst(out);
		}
		return this;
	}

	@Override
	public T first() {
		return _pr.peek();
	}

	@Override
	public T last() {
		return _sf.peek();
	}

	@Override
	public View<T> removeFirst() {
		T val = _pr.remove();
		if(_pr.size() == 0) {
			//pull up a node from mid
			View<Node3<T>> cellar = _mid.removeFirst();
			if(cellar.element() == null) {
				//mid was Empty<Node3<T>>
				//turn _sf into a tree
				return new View<T>(val, _sf.toTree());
			}
			else {
				_mid = cellar.tree();
				_pr = new Digit<T>(cellar.element());
			}
		}
		return new View<T>(val, this);
	}

	@Override
	public View<T> removeLast() {
		T val = _sf.remove();
		if(_sf.size() == 0) {
			//pull up a node from mid
			View<Node3<T>> cellar = _mid.removeLast();
			if(cellar.element() == null) {
				//mid was Empty<Node3<T>>
				//turn _sf into a tree
				return new View<T>(val, _pr.toTree());
			}
			else {
				_mid = cellar.tree();
				_sf = new BackDigit<T>(cellar.element());
			}
		}
		return new View<T>(val, this);
	}
	
	public String toString() {
		return _pr.toString() + _mid.toString() + _sf.toString();
	}
}
