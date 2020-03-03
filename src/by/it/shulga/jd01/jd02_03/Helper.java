package by.it.shulga.jd01.jd02_03;


import java.util.Map;
import java.util.Random;

class Helper {

    private static Random generator = new Random(System.nanoTime());
    static final String SPACE_BUFFER = "                   ";

    static void sleep(int timiout) {

        try {
            Thread.sleep(timiout / Dispatcher.K_SPEED);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    static void cashierSleep(int timiout) throws InterruptedException {

        Thread.sleep(timiout / Dispatcher.K_SPEED);

    }

    static int random(int min, int max) {
        return min + generator.nextInt(max - min + 1);
    }

    static int random(int max) {
        return generator.nextInt(max + 1);
    }


    static void printChek(Cashier cashier, Buyer buyer) {
        StringBuilder buffer = new StringBuilder();
        StringBuffer out = new StringBuffer();

        buffer.append(SPACE_BUFFER.repeat(Math.max(0, cashier.getNum())));
        out.append(buffer).append(cashier);
        double sum = 0.0;
        for (Map.Entry<String, Double> entry : buyer.backet.entrySet()) {
            sum += entry.getValue();
            out.append("\n").append(buffer).append(String.format("%s %6.2f", entry.getKey(), entry.getValue()));
        }
        out.append("\n").append(buffer).append(String.format("Сумма: %6.2f", Math.round(sum * 100.0) / 100.0));
        System.out.println(out);
        printAllCash();
    }

    private static void printAllCash() {
        StringBuilder buffer = new StringBuilder();
        buffer.append(SPACE_BUFFER.repeat(7));
        double sum = Dispatcher.getAllCash();
        System.out.println(buffer.append(String.format("Общая касса : %6.2f", sum)));
    }

    static void printQueue() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(SPACE_BUFFER.repeat(6));
        int countBuyer = QueueBuyer.getLength();
        int countPens = QueuePensioneer.getLength();
        System.out.println(buffer
                .append("Покупателей в очереди: ")
                .append(countBuyer + countPens)
                .append("(")
                .append(countBuyer)
                .append("+")
                .append(countPens)
                .append(")"));
    }
}


