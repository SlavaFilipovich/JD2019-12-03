package by.it.kuzmichalex.jd02_02;

class  Cashier implements Runnable {
    private String cashierName;
    private int cashierNumber;
    private volatile boolean isPaused = false;
    private double totalAmount = 0;
    private double totalBuyers = 0;
    volatile String state;

    public Cashier(int n) {

        cashierName = "Cashier " + n + " ";
        cashierNumber = n;
        isPaused = true;
        Logger.addToLog(this + "created", cashierNumber);
        //System.out.println(cashierName + "created");
    }

    @Override
    public void run() {
        while (!Dispatcher.isAllBuyersDone()) {
            if (isPaused) {
                Logger.addToLog("......." + this + ".......\n.... PAUSED wait(); ....\n........................", cashierNumber);
                synchronized (this) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            //getBuyerFromQueue синхронизирован монитором очереди. поэтому этот buyer - только этого потока
            Buyer buyer = BuyerQueue.getBuyerFromQueue();
            if (buyer != null) {
                totalAmount += buyer.getBuyerTotalAmount();
                totalBuyers++;
                Logger.addToLog("------------------------\n" + this + "starts serving\n" + buyer + "\n------------------------", cashierNumber);
                state = "serving...";
                TimeHelper.sleep(TimeHelper.getRandom(2000, 5000));
                Logger.addToLog(buyer.getCheck(), cashierNumber);
                Dispatcher.addMarketAmount(buyer.getBuyerTotalAmount());
                state = "";
                synchronized (buyer) {    //buyer-ы в очереди wait, поэтому делаем им notify
                    buyer.notify();
                }
            } //else goWait(" himself");
        }
        //System.out.println(this + "done");
        Logger.addToLog(this + "finished ", cashierNumber);
    }

    public String toString() {
        return cashierName;
    }

    public void goWait(String reason) {
        if (cashierNumber == 0) return;     //Этот кассир невезучий. он не может быть на перекуре
        synchronized (this) {
            if (!isPaused) {
                Logger.addToLog(".. " + this + " ..\n.. TO PAUSE by" + reason, cashierNumber);
                //System.out.println( this + "Paused by " + reason);
                isPaused = true;
            }
        }
    }

    public void goNotify() {
        Logger.addToLog(this + "RESUME notify();", cashierNumber);
        synchronized (this) {
            isPaused = false;
            this.notify();
        }
    }


}
