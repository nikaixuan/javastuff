package algo;

import java.util.concurrent.Semaphore;

public class PCModel {

    Semaphore fullBuffers = new Semaphore(0); /*仓库中已填满的货架个数*/
    Semaphore emptyBuffers = new Semaphore(3);/*仓库货架空闲个数*/
    Semaphore mutex = new Semaphore(1); /*生产-消费互斥信号*/

    void Producer() throws InterruptedException {
        while(true) {
            /*生产产品item*/
            emptyBuffers.acquire();
            mutex.acquire();
            /*item存入仓库buffer*/
            mutex.release();
            fullBuffers.release();
        }
    }

    void Consumer() throws InterruptedException {
        while(true) {
            fullBuffers.acquire();
            mutex.acquire();
            /*从仓库buffer中取产品item*/
            mutex.release();
            emptyBuffers.release();
            /*消费产品item*/
        }
    }

    public static void main(String[] args) {

    }
}


