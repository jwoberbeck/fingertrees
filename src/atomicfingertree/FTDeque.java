package atomicfingertree;

import java.util.concurrent.atomic.AtomicReference;

public class FTDeque<T>{
	
	private AtomicReference<FingerTree<T>> _tree;
	private int _size;
	
	public FTDeque() {
		_tree = new AtomicReference<FingerTree<T>>(new Empty<T>());
		_size = 0;
	}
	
	public int size() {
		return _size;
	}
	
	public void insertLast(T e) {
		FingerTree<T> old = _tree.get();
		FingerTree<T> curr = old.insertLast(e);
		while(!_tree.compareAndSet(old, curr)) {
			old = _tree.get();
			curr = old.insertLast(e);
		}
	}
	
	public void insertFirst(T e) {
		FingerTree<T> old = _tree.get();
		FingerTree<T> curr = old.insertFirst(e);
		while(!_tree.compareAndSet(old, curr)) {
			old = _tree.get();
			curr = old.insertFirst(e);
		}
	}
	
	public T removeFirst() {
		FingerTree<T> old = _tree.get();
		View<T> output = old.removeFirst();
		T ret = output.element();
		FingerTree<T> curr = output.tree();
		while(!_tree.compareAndSet(old, curr)) {
			old = _tree.get();
			output = old.removeFirst();
			curr = output.tree();
			ret = output.element();
		}
		return ret;
	}
	
	public T removeLast() {
		FingerTree<T> old = _tree.get();
		View<T> output = old.removeLast();
		T ret = output.element();
		FingerTree<T> curr = output.tree();
		while(!_tree.compareAndSet(old, curr)) {
			old = _tree.get();
			output = old.removeLast();
			curr = output.tree();
			ret = output.element();
		}
		return ret;
	}
	
	public T peekFirst() {
		//printTree();
		return _tree.get().first();
	}
	
	public T peekLast() {
		//printTree();
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
		System.out.println("Testing complete.");
	}
}
