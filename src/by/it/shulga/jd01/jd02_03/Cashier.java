package by.it.shulga.jd01.jd02_03;

import java.util.Map;


import static by.it.shulga.jd01.jd02_03.Dispatcher.addAllCash;
import static by.it.shulga.jd01.jd02_03.Helper.cashierSleep;
import static by.it.shulga.jd01.jd02_03.Helper.random;

public class Cashier implements Runnable{

    private String name;
    private int number;

    public Cashier(int number) {
        name="--Кассир № "+number+" ";
        this.number=number;
        Dispatcher.cashOpen();
    }

    @SuppressWarnings("SynchronizationOnLocalVariableOrMethodParameter")
    @Override
    public void run() {
        System.out.println(this+"открыт");
        while (Dispatcher.marketNotClosed()){
            Buyer buyer;
            buyer = QueuePensioneer.extract();
            if(buyer==null)
                buyer = QueueBuyer.extract();
            if (buyer!=null) {
                System.out.println(this + "обслуживает " + buyer);
                Double chek=0.0;
                for (Map.Entry<String, Double> entry : buyer.backet.entrySet()) {
                    chek+=entry.getValue();
                }
                addAllCash(chek);
                //System.out.println(this + "сумма чека: " + chek);
                Helper.printChek(this,buyer);
                try {
                    cashierSleep(random(2000, 5000));
                } catch (InterruptedException e) {
                    System.out.println(this+"закрывается");
                    Dispatcher.cashClose();
                    return;
                }
                finally {
                    System.out.println(this + "окончил обслуживание " + buyer);
                    synchronized (buyer) {
                        buyer.setWaitingFlag(false);
                        buyer.notify();
                    }
                }

            }
            try {
                cashierSleep(1);
            } catch (InterruptedException e){
                System.out.println(this+"закрыт");
                Dispatcher.cashClose();
                return;
            }

        }
        System.out.println(this+"закрыт");
    }

    @Override
    public String toString() {
        return name;
    }

    public int getNum() {
        return number;
    }
}