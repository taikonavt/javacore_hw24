public class Main {
    private static final Object monitor = new Object();
    private static char currentChar = 'A';

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    synchronized (monitor){
                        while (currentChar != 'A'){
                            monitor.wait();
                        }
                        System.out.print("A");
                        currentChar = 'B';
                        monitor.notifyAll();
                    }
                }
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    synchronized (monitor){
                        while (currentChar != 'B'){
                            monitor.wait();
                        }
                        System.out.print("B");
                        currentChar = 'C';
                        monitor.notifyAll();
                    }
                }
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    synchronized (monitor){
                        while (currentChar != 'C'){
                            monitor.wait();
                        }
                        System.out.print("C");
                        currentChar = 'A';
                        monitor.notifyAll();
                    }
                }
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }).start();
    }
}
