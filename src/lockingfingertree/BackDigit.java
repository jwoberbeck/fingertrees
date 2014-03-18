package lockingfingertree;

/**
 * Inherits from Digit, for the "back" side of the tree.  "a" is the last
 * element in the digit.
 * @author jwoberbe
 *
 * @param <T>
 */

public class BackDigit<T> {
	
	private T _a, _b, _c, _d;
	private int _size;
	
	public BackDigit(T a) {
		_a = a;
		_size = 1;
	}
	
	public BackDigit(T a, T b) {
		_a = a;
		_b = b;
		_size = 2;
	}
	
	public BackDigit(T a, T b, T c) {
		_a = a;
		_b = b;
		_c = c;
		_size = 3;
	}
	
	public BackDigit(T a, T b, T c, T d) {
		_a = a;
		_b = b;
		_c = c;
		_d = d;
		_size = 4;
	}
	
	public BackDigit(Node3<T> node) {
		if(node.c() == null) {
			_b = node.a();
			_a = node.b();
			_size = 2;
		}
		else{
			_c = node.a();
			_b = node.b();
			_a = node.c();
			_size = 3;
		}
	}
	
	/*
	 * Turns a digit into a tree, and returns it
	 */
	public FingerTree<T> toTree() {
		switch(_size) {
		case 1:
			return new Single<T>(_a);
		case 2:
			return new Deep<T>(new Digit<T>(_b), new Empty<Node3<T>>(), new BackDigit<T>(_a));
		case 3:
			return new Deep<T>(new Digit<T>(_c), new Empty<Node3<T>>(), new BackDigit<T>(_a, _b));
		case 4:
			return new Deep<T>(new Digit<T>(_d), new Empty<Node3<T>>(), new BackDigit<T>(_a, _b, _c));
		}
		return null; //never reached
	}
	
	public int size() {
		return _size;
	}
	
	/*
	 * Inserts an element of type T, and
	 * if said insertion fills the digit,
	 * returns a Node3<T> to be inserted
	 * into the next level of the tree.
	 */
	public Node3<T> insert(T x) {
		switch(_size) {
		case 1:
			_b = _a;
			_a = x;
			_size = 2;
			return null;
		case 2:
			_c = _b;
			_b = _a;
			_a = x;
			_size = 3;
			return null;
		case 3:
			_d = _c;
			_c = _b;
			_b = _a;
			_a = x;
			_size = 4;
			return null;
		case 4:
			Node3<T> ret = new Node3<T>(_d, _c, _b);
			_d = null;
			_c = null;
			_b = _a;
			_a = x;
			_size = 2;
			return ret;
		}
		return null; //never reached
	}
	
	public T remove() {
		T ret = null;
		switch(_size) {
		case 1:
			ret = _a;
			_a = null;
			_size = 0;
			break;
		case 2:
			ret = _a;
			_a = _b;
			_b = null;
			_size = 1;
			break;
		case 3:
			ret = _a;
			_a = _b;
			_b = _c;
			_c = null;
			_size = 2;
			break;
		case 4:
			ret = _a;
			_a = _b;
			_b = _c;
			_c = _d;
			_d = null;
			_size = 3;
			break;
		}
		return ret;
	}
	
	public T peek() {
		return _a;
	}
	
	public String toString() {
		try {
			switch(_size) {
			case 1:
				return " " + _a.toString() + " ";
			case 2:
				return " " + _b.toString() + " " +  _a.toString() + " ";
			case 3:
				return " " + _c.toString() + " " + _b.toString() + " " + _a.toString() + " ";
			case 4:
				return " " + _d.toString() + " " + _c.toString() + " " + _b.toString() + " " + _a.toString() + " ";
			}
			return "";
		}
		catch (NullPointerException e) {
			System.out.println("Null pointer detected! digit looks like:");
			if(_a != null) {
				System.out.println("_a is " + _a);
			}
			else {
				System.out.println("_a is null");
			}
			if(_b != null) {
				System.out.println("_b is " + _b);
			}
			else {
				System.out.println("_b is null");
			}
			if(_c != null) {
				System.out.println("_c is " + _c);
			}
			else {
				System.out.println("_c is null");
			}
			if(_d != null) {
				System.out.println("_d is " + _d);
			}
			else {
				System.out.println("_d is null");
			}
			System.out.println("size is " + _size);
			throw(e);
		}
	}

}