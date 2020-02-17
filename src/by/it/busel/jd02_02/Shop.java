package by.it.busel.jd02_02;

import java.util.ArrayList;
import java.util.List;

class Shop {

    private static List<Thread> subThreads = new ArrayList<>(1000);

    private static int subThreadId = 0;

    public static void main(String[] args) {
        int thCount = Thread.activeCount();
        String mainThreadName = Shop.class.getSimpleName();
        informAboutOpening(mainThreadName);
        addCashiers(2);
        int second = 0;
        while (Dispatcher.shopDoorsAreStillOpened()) {
            int currentBuyersNumber = Dispatcher.getBuyersNumberInside();
            int numberOfBuyersToEnter = getNumberOfBuyersToEnter(second, currentBuyersNumber);
            System.out.println("Current second: " + second++ + "; Buyers inside the Shop: " + currentBuyersNumber
                    + "; Buyers to enter: " + numberOfBuyersToEnter);
            letBuyersGetIn(numberOfBuyersToEnter);
            Helper.sleep(1000);
        }
        informAboutDoorsClosure(mainThreadName);
        executeUntilAllSubThreadsFinished(thCount);
        informAboutClosureOfStore(mainThreadName);
    }

    private static void addCashiers(int number) {
        for (int i = 1; i <= number; i++) {
            Cashier cashier = new Cashier(i);
            subThreads.add(cashier);
            cashier.start();
        }
    }

    private static int getNumberOfBuyersToEnter(int second, int currentBuyersNumber) {
        int numberOfBuyersToEnter;
        int simplifiedTimeUnit = second % 60;
//        int currentBuyersNumber = Dispatcher.get();
        if (simplifiedTimeUnit < 30) {
            numberOfBuyersToEnter = simplifiedTimeUnit + 10 - currentBuyersNumber;
        } else {
            numberOfBuyersToEnter = 40 + (30 - simplifiedTimeUnit) - currentBuyersNumber;
        }
        if (numberOfBuyersToEnter < 0) numberOfBuyersToEnter = 0;
        return numberOfBuyersToEnter;
    }

    private static void letBuyersGetIn(int numberOfBuyersToEnter) {
        for (int i = 0; i < numberOfBuyersToEnter; i++) {
            if (Dispatcher.shopDoorsAreStillOpened()) {
                Buyer buyer = new Buyer(++subThreadId);
                subThreads.add(buyer);
                buyer.start();
            }
        }
    }

    private static void executeUntilAllSubThreadsFinished(int thCount) {
        while (Thread.activeCount() > thCount) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void informAboutOpening(String mainThreadName) {
        System.out.println("<<<<< The \"" + mainThreadName + "\" has opened >>>>>");
    }

    private static void informAboutDoorsClosure(String mainThreadName) {
        System.out.println("===== The dispatcher of the \"" + mainThreadName + "\" has shut the doors =====");
        System.out.println("===== A number of Buyers remaining inside the store is " + Dispatcher.getBuyersNumberInside() + " ======");
    }

    private static void informAboutClosureOfStore(String mainThreadName) {
        System.out.println("<<<<< The \"" + mainThreadName + "\" has closed >>>>>");
        System.out.println("===== A number of Buyers remaining inside the store is " + Dispatcher.getBuyersNumberInside() + " ======");
    }
}