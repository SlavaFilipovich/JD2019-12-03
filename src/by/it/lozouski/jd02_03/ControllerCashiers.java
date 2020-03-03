package by.it.lozouski.jd02_03;

class ControllerCashiers extends Thread {
    @Override
    public void run() {
        while (!Dispetcher.marketClosed()) {
            Help.sleep(500);
            int sizeQueueBuyers = QueueBuyer.getBuyersQueue().size();
            int currentOpenedCashiers = Cashier.pieceCashiers;
            if (currentOpenedCashiers == 0){
                if (sizeQueueBuyers > 0) {
                    synchronized (Cashier.monitorCashier) {
                        Cashier.monitorCashier.notify();
                    }
                }
            }else if (sizeQueueBuyers / currentOpenedCashiers > 5){
                synchronized (Cashier.monitorCashier){
                    Cashier.monitorCashier.notify();
                }
            }
            synchronized (Cashier.monitorCashier){
                Cashier.monitorCashier.notifyAll();
            }
        }
    }
}
