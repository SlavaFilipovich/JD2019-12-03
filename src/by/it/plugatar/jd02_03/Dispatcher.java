package by.it.plugatar.jd02_03;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class Dispatcher {
    static final int K_SPEED = 50;     //коэффициент ускорения (для отладки)
    static AtomicInteger cashiersNumber = new AtomicInteger(1);     // кассиры
    static AtomicInteger cashiersCount = new AtomicInteger(0);     // кассы
    static AtomicInteger buyersNumber = new AtomicInteger(1);     //покупатели
    static AtomicInteger buyersCount = new AtomicInteger(0);     //количество покупателей в магазе
    static AtomicInteger buyersComplete = new AtomicInteger(0);
    static AtomicInteger overallPrice = new AtomicInteger(0);
    private static final int plan = 100;     //план работы магазина
    static Semaphore semaphore = new Semaphore(0);
    static Semaphore semaphoreBuyers = new Semaphore(20);
    static Semaphore semaphoreBaskets = new Semaphore(50);
    static Semaphore semaphoreConsole = new Semaphore(1);

    static boolean planComplete() {
        return buyersComplete.get() == plan;
    }

    static boolean marketIsClosed() {
        return buyersCount.get() + buyersComplete.get() >= plan;
    }
}