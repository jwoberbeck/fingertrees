package lockingfingertree;

public class Node3<T> extends Node<T>{
	private T _a, _b, _c;
	
	public Node3(T a, T b, T c) {
		super();
		_a = a;
		_b = b;
		_c = c;
	}
	
	public T a() {
		return _a;
	}
	
	public T b() {
		return _b;
	}
	
	public T c() {
		return _c;
	}
	
	public String toString() {
		return " <" + _a + " " + _b + " " + _c + "> ";
	}

}
