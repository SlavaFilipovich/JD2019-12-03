package by.it.samuseva.jd02_02;

import java.util.ArrayDeque;

public class QueueBuyer {
    private static ArrayDeque<Buyer> queue = new  ArrayDeque<>();
    private static ArrayDeque<Buyer> queuePensioner  =new ArrayDeque<>();

    static synchronized void add(Buyer buyer){
        if (buyer.pensioner){
            queuePensioner.addLast(buyer);
        } else queue.addLast(buyer);
    }

    static synchronized Buyer extract(){
        if (queuePensioner.size() != 0) return queuePensioner.pollFirst();
        else return queue.pollFirst();
    }

     static int getQueueSize() {
        return queue.size();
    }

    static int getQueuePensionerSize() {
        return queuePensioner.size();
    }
}
