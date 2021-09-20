package concurrent;

import java.util.concurrent.Semaphore;

class FooBar {
    private int n;

    Semaphore bar = new Semaphore(0);
    Semaphore foo = new Semaphore(1);
    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            foo.acquire();
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            // after this release, the bar.acquire() will out of block
            bar.release();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            // code will block here every iteration because bar semaphore is 0
            bar.acquire();
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            foo.release();
        }
    }
}
