import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main1 {
    private static final Object monitor = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (monitor){
            for (int i = 0; i < 10; i++) {
                    try {
                        writeFile('A');
                        monitor.notifyAll();
                        monitor.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                monitor.notifyAll();
            }
        }).start();
        new Thread(() -> {
            synchronized (monitor){
            for (int i = 0; i < 10; i++) {
                    try {
                        writeFile('B');
                        monitor.notifyAll();
                        monitor.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                monitor.notifyAll();
            }
        }).start();
        new Thread(() -> {
            synchronized (monitor){
            for (int i = 0; i < 10; i++) {
                    try {
                        writeFile('C');
                        monitor.notifyAll();
                        monitor.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                monitor.notifyAll();
            }
        }).start();
    }

    private static void writeFile(char ch){
        File file = new File("files/file.txt");
        long time = System.currentTimeMillis();
        FileWriter bw = null;
        try {
            bw = new FileWriter(file, true);
            bw.write('\n');
            while ((System.currentTimeMillis() - time) < 10){
                bw.write(ch);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally {
            try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
