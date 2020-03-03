package by.it.plugatar.jd02_03;

public class Buyer extends Thread implements IBuyer,IUseBasket {
    private String name;
    boolean pensioneer = false;
    Basket basket = new Basket();

    public Buyer(int number) {
        Dispatcher.buyersCount.incrementAndGet();
        this.pensioneer = Math.random() < 0.25;
        if (this.pensioneer) this.name= "Buyer №:" + number + "(pensioneer)";
        else this.name = "Buyer №:" + number;
    }

    @Override
    public void run() {
        try {
            enterToMarket();
            takeBacket();
            Goods goods = new Goods();
            int priceListSize = goods.getPriceListSize();
            int quantity = RandomHelper.random(1, 4);
            for (int i = 0; i < quantity; i++) {
                chooseGoods();
                putGoodsToBasket(goods, priceListSize);
            }
            goToQueue();
            goOut();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
        //покупатель отправляется в очередь
        private void goToQueue() throws InterruptedException {
            Dispatcher.semaphoreBuyers.release();
            Dispatcher.semaphoreConsole.acquire();
            System.out.println(this + " went to queue");
            Dispatcher.semaphoreConsole.release();
            QueueBuyer.putToQueue(this);
            try {
                Dispatcher.semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void enterToMarket() throws InterruptedException {
            Dispatcher.semaphoreConsole.acquire();
            System.out.println(this + " entered the market");
            Dispatcher.semaphoreConsole.release();
        }

        @Override
        public void chooseGoods() throws InterruptedException {
            int time = RandomHelper.random(500, 2000);
            RandomHelper.sleep(this.pensioneer ? (int) (time * 1.5) : time);
            Dispatcher.semaphoreConsole.acquire();
            System.out.println(this + " is choosing goods");
            Dispatcher.semaphoreConsole.release();
        }

        @Override
        public void goOut() throws InterruptedException {
            Dispatcher.semaphoreBaskets.release();
            Dispatcher.buyersCount.decrementAndGet();
            Dispatcher.buyersComplete.incrementAndGet();
            Dispatcher.semaphoreConsole.acquire();
            System.out.println(this + " exited the market");
            Dispatcher.semaphoreConsole.release();
        }


    @Override
    public void takeBacket() throws InterruptedException {
        try {
            Dispatcher.semaphoreBaskets.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Dispatcher.semaphoreConsole.acquire();
        System.out.println(this + " took basket");
        Dispatcher.semaphoreConsole.release();
        int time = RandomHelper.random(100, 200);
        RandomHelper.sleep(this.pensioneer ? (int) (time * 1.5) : time);
        try {
            Dispatcher.semaphoreBuyers.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
        public void putGoodsToBasket(Goods listOfGoods, int listSize) throws InterruptedException {
            String goods = RandomHelper.getRandomGoods(listSize);
            Dispatcher.semaphoreConsole.acquire();
            System.out.println(this + " took " + goods);
            Dispatcher.semaphoreConsole.release();
            int time = RandomHelper.random(100, 200);
            RandomHelper.sleep(this.pensioneer ? (int) (time * 1.5) : time);
            int price = (int) listOfGoods.getPrice(goods);
            this.basket.goods.put(goods, (double) price);
        }

        @Override
        public String toString() {
            return this.getName();
        }

}