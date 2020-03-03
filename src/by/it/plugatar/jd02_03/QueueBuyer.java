package by.it.plugatar.jd02_03;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class QueueBuyer {
    static ArrayBlockingQueue<Buyer> queue = new ArrayBlockingQueue<>(30);
    private static Queue<Buyer> pensioneerQueue = new LinkedList<>();

    static void putToQueue(Buyer buyer) {
        if (buyer.pensioneer) pensioneerQueue.add(buyer);
        else {
            try {
                queue.put(buyer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    static Buyer pollBuyer() {
        if (pensioneerQueue.size() > 0) return pensioneerQueue.poll();
        return queue.poll();
    }

}