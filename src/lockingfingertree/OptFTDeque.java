package lockingfingertree;

import java.util.concurrent.atomic.AtomicReference;

public class OptFTDeque<T>{
	
	private AtomicReference<FingerTree<T>> _tree;
	private int _size;
	
	public OptFTDeque() {
		_tree = new AtomicReference<FingerTree<T>>(new Empty<T>());
		_size = 0;
	}
	
	public int size() {
		return _size;
	}
	
	public void insertLast(T e) {
		FingerTree<T> old = _tree.get();
		FingerTree<T> temp = old.insertFirst(e);
		while(!_tree.compareAndSet(old, temp)) {
			old = _tree.get();
			temp = old.insertFirst(e);
		}
	}
	
	public void insertFirst(T e) {
		FingerTree<T> old = _tree.get();
		FingerTree<T> temp = old.insertLast(e);
		while(!_tree.compareAndSet(old, temp)) {
			old = _tree.get();
			temp = old.insertLast(e);
		}
	}
	
	public T removeFirst() {
		printTree();
		FingerTree<T> old = _tree.get();
		View<T> output = old.removeFirst();
		T ret = output.element();
		while(_tree.compareAndSet(old, output.tree())) {
			old = _tree.get();
			output = old.removeFirst();
			ret = output.element();
		}
		return ret;
	}
	
	public T removeLast() {
		printTree();
		FingerTree<T> old = _tree.get();
		View<T> output = old.removeLast();
		T ret = output.element();
		while(_tree.compareAndSet(old, output.tree())) {
			old = _tree.get();
			output = old.removeLast();
			ret = output.element();
		}
		return ret;
	}
	
	public T peekFirst() {
		printTree();
		return _tree.get().first();
	}
	
	public T peekLast() {
		printTree();
		return _tree.get().last();
	}
	
	/*
	 * Creates a new FingerTree, for testing purposes
	 */
	public void refresh() {
		_tree = new AtomicReference<FingerTree<T>>(new Empty<T>());
		_size = 0;
	}
	
	public void printTree() {
		System.out.println(_tree.get().toString());
	}
	
	public static void main(String[] args) {
		FTDeque<Integer> d = new FTDeque<Integer>();
		FTTester tester = new FTTester(d);
		tester.runTests();
	}
}
