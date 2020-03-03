package by.it.tarasevich.jd02_03;


import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class QueueBuyer {
    private static BlockingDeque<Buyer> queue = new LinkedBlockingDeque<>(30);

    static void add(Buyer buyer) {
        queue.addLast(buyer);
    }

    static Buyer extract() {
        return queue.pollFirst();
    }
}
