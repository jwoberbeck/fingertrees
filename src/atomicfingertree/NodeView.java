package atomicfingertree;

public class NodeView<T> {

	private Node3<T> _node;
	private Digit<T> _digit;
	
	public NodeView(Node3<T> elem, Digit<T> digit) {
		_node = elem;
		_digit = digit;
	}
	
	public Node3<T> node() {
		return _node;
	}
	
	public Digit<T> digit() {
		return _digit;
	}
}
