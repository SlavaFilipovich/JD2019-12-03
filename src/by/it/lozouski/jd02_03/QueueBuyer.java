package by.it.lozouski.jd02_03;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

class QueueBuyer {
    private static BlockingDeque<Buyer> buyersQueue = new LinkedBlockingDeque<>(30);

    public static BlockingDeque<Buyer> getBuyersQueue() {
        return buyersQueue;
    }

    static void addBuyerToQueue(Buyer currBuyer) {
        buyersQueue.add(currBuyer);
    }

    static Buyer extractBuyerFromQueue() {
        return buyersQueue.pollFirst();
    }
}
