package by.it.cherkas.jd02_03;

import java.util.ArrayDeque;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class QueueBuyer {

    private static BlockingDeque<Buyer> queue = new LinkedBlockingDeque<>(30);

    static synchronized void add(Buyer buyer){
        queue.addLast(buyer);
    }
    static synchronized Buyer extract(){
        return queue.pollFirst();
    }
}
