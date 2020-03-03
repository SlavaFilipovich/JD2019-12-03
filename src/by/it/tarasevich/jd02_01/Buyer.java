package by.it.tarasevich.jd02_01;


class Buyer extends Thread implements IBuyer {

    public Buyer(int number) {
        super("Buyer №" + number + " ");
        Dispatcher.countBuyer++;
    }

    @Override
    public void run() {
        enterToMarket();
        chooseGoods();
        goOut();
    }

    @Override
    public void enterToMarket() {
        System.out.println(this + "enter to the market");
    }

    @Override
    public void chooseGoods() {
        System.out.println(this + "started choosing by goods");
        int timeout = Helper.random(500, 2000);
        Helper.sleep(timeout);
        System.out.println(this + "finshed choosing by goods");
    }

    @Override
    public void goOut() {
        System.out.println(this + "go from the market");
        Dispatcher.countBuyer--;
    }


    @Override
    public String toString() {
        return this.getName();
    }
}


