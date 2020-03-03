package by.it.shulga.jd01.jd02_02;

import java.util.ArrayDeque;

class BuyerQueue {
    private final ArrayDeque<Buyer> buyerQueue = new ArrayDeque<>(96);

    private final ArrayDeque<Buyer> pensionerQueue = new ArrayDeque<>(48);

    public synchronized void addToQueue(Buyer buyer) {
        buyerQueue.addLast(buyer);
    }

    public synchronized Buyer getFirstBuyer() {
        return buyerQueue.pollFirst();
    }

    public synchronized void addToPensionerQueue(Buyer buyer) {
        pensionerQueue.addLast(buyer);
    }

    public synchronized Buyer getFirstPensioner() {
        return pensionerQueue.pollFirst();
    }

    public synchronized int getBuyerQueueSize() {
        return buyerQueue.size();
    }

    public synchronized int getPensionerQueueSize() {
        return pensionerQueue.size();
    }
}
