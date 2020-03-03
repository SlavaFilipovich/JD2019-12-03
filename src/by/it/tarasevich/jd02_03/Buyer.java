package by.it.tarasevich.jd02_03;


class Buyer extends Thread implements IBuyer {

    private boolean waitingFlag = false;

    void setWaitingFlag(boolean waitingFlag) {
        this.waitingFlag = waitingFlag;
    }

    public Buyer(int number) {
        super("Buyer №" + number + " ");
        Dispatcher.buyerEnter();
    }

    @Override
    public void run() {
        enterToMarket();
        chooseGoods();
        goToQueue();
        goOut();
    }

    @Override
    public void enterToMarket() {
        System.out.println(this + "enter to the market");
    }

    @Override
    public void chooseGoods() {
        System.out.println(this + "started to choose goods");
        int timeout = Helper.random(500, 2000);
        Helper.sleep(timeout);
        System.out.println(this + "finished to choose goods");
    }

    @Override
    public void goToQueue() {
        System.out.println(this + " go to Queue");
        synchronized (this) {
            QueueBuyer.add(this);
            this.waitingFlag = true;
            while (this.waitingFlag)
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }
    }

    @Override
    public void goOut() {
        System.out.println(this + "left the market");
        Dispatcher.buyerLeave();
    }


    @Override
    public String toString() {
        return this.getName();
    }
}

