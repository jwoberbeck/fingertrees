package lockingfingertree;

import java.util.Random;

public class ParallelTester {
	
	private static final int TEST_THREADS = 32;
    private static final int TEST_OPERATIONS = 1000000;
    private static final int MAX_NUMBER = 50;
    
    public static final FTDeque<Integer> ft = 
            new FTDeque<Integer>();
    
    public static class Tester70 implements Runnable {
        private Random randomGenerator = new Random();
        private int _opnum;

        public Tester70(int opnum) {
        	_opnum = opnum;
        }
        
        public void run() {
            for (int i = 0; i < _opnum; i++) {
                // Pick a random operation.
                int op = randomGenerator.nextInt(200);
                if (op < 70) {
                	ft.peekFirst();
                	continue;
                }
                else if (op < 140) {
                	ft.peekLast();
                    continue;
                }
                Integer key = new Integer(randomGenerator.nextInt(MAX_NUMBER));
                if (op < 167) {
                	ft.insertFirst(key);
                    continue;
                }
                else if (op < 194) {
                	ft.insertLast(key);
                	continue;
                }
                else if (op < 197) {
                	ft.removeFirst();
                	continue;
                }
                else {
                	ft.removeLast();
                	continue;
                }
            }
        }
    }
    
    public static class Tester90 implements Runnable {
        private Random randomGenerator = new Random();
        private int _opnum;

        public Tester90(int opnum) {
        	_opnum = opnum;
        }
        
        public void run() {
            for (int i = 0; i < _opnum; i++) {
                // Pick a random operation.
                int op = randomGenerator.nextInt(200);
                if (op < 90) {
                	ft.peekFirst();
                	continue;
                }
                else if (op < 180) {
                	ft.peekLast();
                    continue;
                }
                Integer key = new Integer(randomGenerator.nextInt(MAX_NUMBER));
                if (op < 189) {
                	ft.insertFirst(key);
                    continue;
                }
                else if (op < 198) {
                	ft.insertLast(key);
                	continue;
                }
                else if (op < 199) {
                	ft.removeFirst();
                	continue;
                }
                else {
                	ft.removeLast();
                	continue;
                }
            }
        }
    }
    
    public static class Tester100 implements Runnable {
        private Random randomGenerator = new Random();
        private int _opnum;

        public Tester100(int opnum) {
        	_opnum = opnum;
        }
        
        public void run() {
            for (int i = 0; i < _opnum; i++) {
                // Pick a random operation.
                int op = randomGenerator.nextInt(200);
                if (op < 100) {
                	ft.peekFirst();
                }
                else {
                	ft.peekLast();
                }
            }
        }
    }
    
    public static void main(String args[]) throws InterruptedException {
    	//warm-up
    	System.out.println("Beginning testing for locking FT:");
    	System.out.println("Tester 90:");
    	Thread[] threads = new Thread[4];
        for (int t = 0; t < threads.length; t++) {
             threads[t] = new Thread(new Tester90(TEST_OPERATIONS/4));
             threads[t].start();
        }

        for (int t = 0; t < threads.length; t++) {
             threads[t].join(); 
        }
    	
        double[] results = new double[(int) (Math.log(TEST_THREADS)/Math.log(2)) + 1];
        int j=0;
        //benchmarking
    	for(int i=1; i<=TEST_THREADS; i=i*2) {
    		ft.refresh();
	        threads = new Thread[i];
	        long startTime = System.nanoTime();
	        for (int t = 0; t < threads.length; t++) {
	             threads[t] = new Thread(new Tester90(TEST_OPERATIONS/i));
	             threads[t].start();
	        }
	
	        for (int t = 0; t < threads.length; t++) {
	             threads[t].join(); 
	        }
	        long endTime = System.nanoTime();
	        results[j] = ((endTime - startTime) / 1e6);
	        j++;
	        System.out.println("Test took " + ((endTime - startTime) / 1e6) + " milliseconds with " + i + " threads.");
    	}
    	
    	
    	//warm-up
    	System.out.println("Tester 70:");
    	threads = new Thread[4];
        for (int t = 0; t < threads.length; t++) {
             threads[t] = new Thread(new Tester70(TEST_OPERATIONS/4));
             threads[t].start();
        }

        for (int t = 0; t < threads.length; t++) {
             threads[t].join(); 
        }
    	
        results = new double[(int) (Math.log(TEST_THREADS)/Math.log(2)) + 1];
        j=0;
        //benchmarking
    	for(int i=1; i<=TEST_THREADS; i=i*2) {
    		ft.refresh();
	        threads = new Thread[i];
	        long startTime = System.nanoTime();
	        for (int t = 0; t < threads.length; t++) {
	             threads[t] = new Thread(new Tester70(TEST_OPERATIONS/i));
	             threads[t].start();
	        }
	
	        for (int t = 0; t < threads.length; t++) {
	             threads[t].join(); 
	        }
	        long endTime = System.nanoTime();
	        results[j] = ((endTime - startTime) / 1e6);
	        j++;
	        System.out.println("Test took " + ((endTime - startTime) / 1e6) + " milliseconds with " + i + " threads.");
    	}
    	
    	//warm-up
    	System.out.println("Tester 100:");
    	threads = new Thread[4];
        for (int t = 0; t < threads.length; t++) {
             threads[t] = new Thread(new Tester100(TEST_OPERATIONS/4));
             threads[t].start();
        }

        for (int t = 0; t < threads.length; t++) {
             threads[t].join(); 
        }
    	
        results = new double[(int) (Math.log(TEST_THREADS)/Math.log(2)) + 1];
        j=0;
        //benchmarking
    	for(int i=1; i<=TEST_THREADS; i=i*2) {
    		ft.refresh();
	        threads = new Thread[i];
	        long startTime = System.nanoTime();
	        for (int t = 0; t < threads.length; t++) {
	             threads[t] = new Thread(new Tester100(TEST_OPERATIONS/i));
	             threads[t].start();
	        }
	
	        for (int t = 0; t < threads.length; t++) {
	             threads[t].join(); 
	        }
	        long endTime = System.nanoTime();
	        results[j] = ((endTime - startTime) / 1e6);
	        j++;
	        System.out.println("Test took " + ((endTime - startTime) / 1e6) + " milliseconds with " + i + " threads.");
    	}

    }

}
