package by.it.shulga.jd01.jd02_03;


import java.util.ArrayList;
import java.util.List;

public class Administrator implements Runnable {

    private String name;

    public Administrator() {
        name = "Администратор ";
    }

    @Override
    public void run() {
        List<Thread> threads = new ArrayList<>(5);
        int plus;
        int openedCashiers=0;
        System.out.println(this + "пришел на работу");
        Cashier cashier = new Cashier(++openedCashiers);
        Thread thread = new Thread(cashier);
        threads.add(thread);
        thread.start();
        Helper.sleep(1000);
        while (Dispatcher.marketNotClosed()) {
            int length = QueueBuyer.getLength() + QueuePensioneer.getLength();
            openedCashiers = Dispatcher.getCashCount();
            if (Dispatcher.getCountBuyer() > 0) plus = 1;
            else plus = 0;
            if (openedCashiers < (length + plus) / 5 && openedCashiers < Dispatcher.ALL_CASHIERS) {
                cashier = new Cashier(++openedCashiers);
                thread = new Thread(cashier);
                threads.add(thread);
                thread.start();
            } else if (openedCashiers > (length + plus) / 5) {
                threads.get(--openedCashiers).interrupt();
            } else {
                Helper.sleep(10);
            }
        }
        for (Thread thread1 : threads) {
            try {
                thread1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(this + "закрывает магазин");
    }

    @Override
    public String toString() {
        return name;
    }
}