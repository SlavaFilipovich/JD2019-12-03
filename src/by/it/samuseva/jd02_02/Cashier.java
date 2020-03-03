package by.it.samuseva.jd02_02;

import java.util.Map;

class Cashier implements Runnable {

    private String name;

    Cashier(int number) {
        name = "***Cashier № "+ number +" ";
    }

    @Override
    public synchronized void run() {
        System.out.println(this + "opened.");
        while (!Dispetcher.marketClosed()){
            Buyer buyer = QueueBuyer.extract();
            if (buyer != null){
                System.out.println(this + "started service" + " " + buyer + ".");
                int timeout = Helper.random(2000, 5000);
                Helper.sleep(timeout);
                serviceBuyer(buyer.getGoodBacket(), buyer);
                System.out.println(this + "finished service " + " " + buyer + ".");
                synchronized (buyer){
                    buyer.notify();
                    buyer.setFlagQueue(false);
                }
            }
            else { Helper.sleep(20);
//                try {
//                    this.wait();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        }
        System.out.println(this + "closed.");
    }

    private void serviceBuyer(Map<String, Integer> goodBacket, Buyer buyer) {
        int sumReceipt = 0;
        for (Map.Entry<String, Integer> entry : goodBacket.entrySet()) {
            System.out.println(this + "take " + entry.getKey()+ " " + entry.getValue()+" from a basket " + buyer) ;
            sumReceipt +=entry.getValue();
        }
        System.out.println(this + buyer.toString() + " check amount = " + sumReceipt + ".");
    }

    @Override
    public String toString() {
        return name;
    }
}
