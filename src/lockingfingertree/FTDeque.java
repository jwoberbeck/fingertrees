package lockingfingertree;

import java.util.concurrent.locks.ReentrantLock;

public class FTDeque<T>{
	
	private FingerTree<T> _tree;
	private final ReentrantLock lock = new ReentrantLock();
	private int _size;
	
	public FTDeque() {
		_tree = new Empty<T>();
		_size = 0;
	}
	
	public int size() {
		return _size;
	}
	
	public void insertLast(T e) {
		lock.lock();
		try {
			_tree = _tree.insertLast(e);
			_size++;
		}
		finally {
			lock.unlock();
		}
	}
	
	public void insertFirst(T e) {
		lock.lock();
		try {
			_tree = _tree.insertFirst(e);
			_size++;
		}
		finally {
			lock.unlock();
		}
	}
	
	public T removeFirst() {
		lock.lock();
		try{
			View<T> ret = _tree.removeFirst();
			_tree = ret.tree();
			_size--;
			return ret.element();
		}
		finally {
			lock.unlock();
		}
	}
	
	public T removeLast() {
		lock.lock();
		try {
			View<T> ret = _tree.removeLast();
			_tree = ret.tree();
			_size--;
			return ret.element();
		}
		finally {
			lock.unlock();
		}
	}
	
	public T peekFirst() {
		return _tree.first();
	}
	
	public T peekLast() {
		return _tree.last();
	}
	
	/*
	 * Creates a new FingerTree, for testing purposes
	 */
	public void refresh() {
		_tree = new Empty<T>();
		_size = 0;
	}
	
	public void printTree() {
		System.out.println(_tree.toString());
	}
	
	public static void main(String[] args) {
		//FTDeque<Integer> d = new FTDeque<Integer>();
		//FTTester tester = new FTTester(d);
		//tester.runTests();
	}
}


