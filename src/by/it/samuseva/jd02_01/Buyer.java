package by.it.samuseva.jd02_01;

class Buyer extends Thread implements IBuyer, IUseBacket {
    boolean pensioneer = false;

    Buyer(int number) {
        super("Buyer №" + " " + number);
    }

    @Override
    public void run() {
        enterToMarket();
        chooseGoods();
        goOut();
    }

    @Override
    public void enterToMarket() {
        System.out.println(this + " " + "entered the market.");
    }

    @Override
    public void chooseGoods() {
        System.out.println(this + " " +"started choosing by goods.");
        takeBacket();
        int timeout = Helper.random(500, 2000);
        if (pensioneer==true) Helper.sleep(timeout*3/2);
        else  Helper.sleep(timeout);
        System.out.println(this + " " + "closed choosing by goods.");

    }

    @Override
    public void goOut() {
        System.out.println(this + " " + "left the market.");

    }

    @Override
    public String toString() { return  this.getName(); }

    @Override
    public void takeBacket() {
        System.out.println(this + " " + "took a basket.");
        int timeout = Helper.random(500, 2000);
        int countGoods = Helper.random(1,4);
        for (int i = 1; i <=countGoods ; i++) {
            putGoodsToBacket();
            if (pensioneer==true) Helper.sleep(timeout*3/2);
            else  Helper.sleep(timeout);
        }

    }

    @Override
    public void putGoodsToBacket() {
        int goodNumber = Helper.random(0, Good.goodsLength()-1);
        String good = Good.getGoods(goodNumber);
        System.out.println(this + " " + "chose" + " " + good + " " + "took and put in a basket.");

    }
}
