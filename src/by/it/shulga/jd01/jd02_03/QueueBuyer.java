package by.it.shulga.jd01.jd02_03;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

import static by.it.shulga.jd01.jd02_03.Helper.sleep;

class QueueBuyer {

    private static BlockingDeque<Buyer> queue=new LinkedBlockingDeque<>(30);


    static void add(Buyer buyer){
        while(!queue.offerLast(buyer)){
            sleep(100);
        }
    }

    static Buyer extract(){
        return queue.pollFirst();
    }

    static int getLength(){
        return queue.size();
    }



}