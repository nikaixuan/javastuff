package concurrent;

import java.util.concurrent.Semaphore;

public class PCModel {

    static Semaphore fullBuffers = new Semaphore(0);
    static Semaphore emptyBuffers = new Semaphore(3);
    static Semaphore mutex = new Semaphore(1);
    static volatile int count = 0;

    static class Producer implements Runnable {

        @Override
        public void run() {
            while(true) {
                try {
                    emptyBuffers.acquire();
                    mutex.acquire();
                    count++;
                    System.out.println("pro still have "+count);
                    mutex.release();
                    fullBuffers.release();
                    Thread.sleep(((int)Math.random())%10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Consumer implements Runnable {

        @Override
        public void run() {
            while(true) {
                try {
                    fullBuffers.acquire();
                    mutex.acquire();
                    count--;
                    System.out.println("con still have "+count + " " +Thread.currentThread().toString());
                    mutex.release();
                    emptyBuffers.release();
                    Thread.sleep(((int)Math.random())%10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new Producer()).start();
        new Thread(new Consumer()).start();
        new Thread(new Consumer()).start();
    }
}


