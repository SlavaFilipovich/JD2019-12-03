package by.it.samuseva.jd02_03;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class QueueBuyer {
    private static BlockingDeque<Buyer> queue = new LinkedBlockingDeque<>(25);
    private static BlockingDeque<Buyer> queuePensioner  =new LinkedBlockingDeque<>(5);

    static void add(Buyer buyer){
        if (buyer.pensioner){
            queuePensioner.addLast(buyer);
        } else queue.addLast(buyer);
    }

    static Buyer extract(){
        if (queuePensioner.size() != 0) return queuePensioner.pollFirst();
        else return queue.pollFirst();
    }

}
