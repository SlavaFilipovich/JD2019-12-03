package by.it.cherkas.jd02_03;

public class Cashier implements Runnable {
        private String name;

        public Cashier(int number){
            name = "Касса № " + number + " ";
        }

        @Override
    public void run(){
            System.out.println(this + "Открыто");
            while (!Dispatcher.marketClosed()){
                Buyer buyer = QueueBuyer.extract();

                if (buyer == null){
                    continue;
                }

                if (buyer != null){
                    System.out.println(this + "начало обслуживания " + buyer);
                    int timeout = Helper.random(2000, 5000);
                    Helper.sleep(timeout);
                    System.out.println(this + "конец обслуживания " + buyer);
                    synchronized (buyer){
                        buyer.setWaitingFlag(false);
                        buyer.notify();
                    }
                }
                else {
                    Helper.sleep(100000);
                }
            }
            System.out.println(this + "Закрыто ");
        }

        @Override
    public String toString(){
            return name;
        }

}
