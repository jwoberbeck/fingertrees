package lockingfingertree;

public class FTTester {
	
	private FTDeque<Integer> _ft;
	
	public FTTester(FTDeque<Integer> ft) {
		_ft = ft;
	}
	
	public void queueTest() {
		for(int i=0; i < 50; i++) {
			_ft.insertLast(i);
		}
		//_ft.printTree();
		for(int j=0; j<50; j++) {
			int top = _ft.removeFirst();
			if(j != top) {
				System.out.println("stackTest failed! Tree:");
				_ft.printTree();
				System.out.println("Item expected: " + j + ", we got: " + top);
			}
		}
		
		for(int i=0; i < 50; i++) {
			_ft.insertFirst(i);
		}
		//_ft.printTree();
		for(int j=0; j<50; j++) {
			int top = _ft.removeLast();
			if(j != top) {
				System.out.println("stackTest failed! Tree:");
				_ft.printTree();
				System.out.println("Item expected: " + j + ", we got: " + top);
			}
		}
		//_ft.printTree();
	}
	
	public void stackTest() {
		for(int i=0; i < 50; i++) {
			_ft.insertFirst(i);
		}
		//_ft.printTree();
		for(int j=49; j>=0; j--) {
			int top = _ft.removeFirst();
			if(j != top) {
				System.out.println("stackTest failed! Tree:");
				_ft.printTree();
				System.out.println("Item expected: " + j + ", we got: " + top);
			}
		}
		
		for(int i=0; i < 50; i++) {
			_ft.insertLast(i);
		}
		//_ft.printTree();
		for(int j=49; j>=0; j--) {
			int top = _ft.removeLast();
			if(j != top) {
				System.out.println("stackTest failed! Tree:");
				_ft.printTree();
				System.out.println("Item expected: " + j + ", we got: " + top);
			}
		}
		//_ft.printTree();
	}
	
	public void runTests() {
		this.stackTest();
		this.queueTest();
		System.out.println("Tests completed!");
	}

}
