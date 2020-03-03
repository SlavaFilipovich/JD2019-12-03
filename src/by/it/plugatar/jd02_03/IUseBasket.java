package by.it.plugatar.jd02_03;

public interface IUseBasket {
    void takeBacket() throws InterruptedException;
    void putGoodsToBasket(Goods goods, int listSize) throws InterruptedException;
}
