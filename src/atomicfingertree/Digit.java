package atomicfingertree;

/**
 * This is a Digit class which serves as the storage location for elements
 * in the tree, as well as storing the Node<T>s at each level of the Spine.
 * Element "a" is always the "front" element of a Digit; that is, a Digit
 * of size one will always have an element at a;
 * 
 * All digits must be of size at least 1.
 * 
 * Digits handle creation of nodes for deeper levels of the tree.
 * @author jwoberbe
 *
 * @param <T>
 */
public class Digit<T> {
	
	protected T _a, _b, _c, _d;
	protected int _size;
	
	public Digit(){
		_size = 0;
	} //implicit super constructor
	
	public Digit(T a) {
		_a = a;
		_size = 1;
	}
	
	public Digit(T a, T b) {
		_a = a;
		_b = b;
		_size = 2;
	}
	
	public Digit(T a, T b, T c) {
		_a = a;
		_b = b;
		_c = c;
		_size = 3;
	}
	
	public Digit(T a, T b, T c, T d) {
		_a = a;
		_b = b;
		_c = c;
		_d = d;
		_size = 4;
	}
	
	/*
	 * Front is false if node is becoming suffix
	 */
	public Digit(Node3<T> node) {
		_a = node.a();
		_b = node.b();
		if(node.c() == null) {
			_size = 2;
		}
		else{
			_c = node.c();
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
			return new Deep<T>(new Digit<T>(_a), new Empty<Node3<T>>(), new BackDigit<T>(_b));
		case 3:
			return new Deep<T>(new Digit<T>(_a, _b), new Empty<Node3<T>>(), new BackDigit<T>(_c));
		case 4:
			return new Deep<T>(new Digit<T>(_a, _b, _c), new Empty<Node3<T>>(), new BackDigit<T>(_d));
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
	public NodeView<T> insert(T x) {
		switch(_size) {
		case 1:
			return new NodeView<T>(null, new Digit<T>(x, _a));
		case 2:
			return new NodeView<T>(null, new Digit<T>(x, _a, _b));
		case 3:
			return new NodeView<T>(null, new Digit<T>(x, _a, _b, _c));
		case 4:
			return new NodeView<T>(new Node3<T>(_b, _c, _d), new Digit<T>(x, _a));
		}
		return null; //never reached
	}
	
	public DigitView<T> remove() {
		switch(_size) {
		case 1:
			return new DigitView<T>(_a, new Digit<T>());
		case 2:
			return new DigitView<T>(_a, new Digit<T>(_b));
		case 3:
			return new DigitView<T>(_a, new Digit<T>(_b, _c));
		case 4:
			return new DigitView<T>(_a, new Digit<T>(_b, _c, _d));
		}
		return null;
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
				return " " + _a.toString() + " " +  _b.toString() + " ";
			case 3:
				return " " + _a.toString() + " " + _b.toString() + " " + _c.toString() + " ";
			case 4:
				return " " + _a.toString() + " " + _b.toString() + " " + _c.toString() + " " + _d.toString() + " ";
			}
			return "NULLDIGIT";
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
