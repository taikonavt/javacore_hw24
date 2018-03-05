public class Mfu {
    private final Object printObj = new Object();
    private final Object scanObj = new Object();

    public void printPages(int numOfPages){
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (printObj){
                    for (int i = 1; i <= numOfPages; i++) {
                        System.out.println("Printed " + i + " pages");
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        }).start();
    }

    public void scanPages(int numOfPages){
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (scanObj){
                    for (int i = 1; i <= numOfPages; i++) {
                        System.out.println("Scanned " + i + " pages");
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        }).start();
    }
}
