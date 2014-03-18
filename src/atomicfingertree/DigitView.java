package atomicfingertree;

public class DigitView<T> {

	private T _elem;
	private Digit<T> _digit;
	
	public DigitView(T elem, Digit<T> digit) {
		_elem = elem;
		_digit = digit;
	}
	
	public T elem() {
		return _elem;
	}
	
	public Digit<T> digit() {
		return _digit;
	}
}