package atomicfingertree;

public class Deep<T> extends FingerTree<T> {
	
	private Digit<T> _pr;
	private Digit<T> _sf;
	private FingerTree<Node3<T>> _mid;
	
	public Deep(Digit<T> pr, FingerTree<Node3<T>> mid, Digit<T> sf) {
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
		NodeView<T> out = _sf.insert(a);
		FingerTree<Node3<T>> mid = _mid;
		if(out.node() != null) {
			mid = _mid.insertLast(out.node());
		}
		return new Deep<T>(_pr, mid, out.digit());
	}

	@Override
	public FingerTree<T> insertFirst(T a) {
		NodeView<T> out = _pr.insert(a);
		FingerTree<Node3<T>> mid = _mid;
		if(out.node() != null) {
			mid = _mid.insertFirst(out.node());
		}
		return new Deep<T>(out.digit(), mid, _sf);
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
		DigitView<T> val = _pr.remove();
		FingerTree<Node3<T>> mid = _mid;
		Digit<T> pr = val.digit();
		if(pr.size() == 0) {
			//pull up a node from mid
			View<Node3<T>> cellar = _mid.removeFirst();
			if(cellar.element() == null) {
				//mid was Empty<Node3<T>>
				//turn _sf into a tree
				return new View<T>(val.elem(), _sf.toTree());
			}
			else {
				mid = cellar.tree();
				pr = new Digit<T>(cellar.element());
			}
		}
		return new View<T>(val.elem(), new Deep<T>(pr, mid, _sf));
	}

	@Override
	public View<T> removeLast() {
		DigitView<T> val = _sf.remove();
		FingerTree<Node3<T>> mid = _mid;
		Digit<T> sf = val.digit();
		if(sf.size() == 0) {
			//pull up a node from mid
			View<Node3<T>> cellar = _mid.removeLast();
			if(cellar.element() == null) {
				//mid was Empty<Node3<T>>
				//turn _sf into a tree
				return new View<T>(val.elem(), _pr.toTree());
			}
			else {
				mid = cellar.tree();
				sf = new BackDigit<T>(cellar.element());
			}
		}
		return new View<T>(val.elem(), new Deep<T>(_pr, mid, sf));
	}
	
	public String toString() {
		return _pr.toString() + _mid.toString() + _sf.toString();
	}
}
