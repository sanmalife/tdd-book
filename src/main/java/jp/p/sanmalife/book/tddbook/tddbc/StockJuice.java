package jp.p.sanmalife.book.tddbook.tddbc;

public class StockJuice {
    public Juice juice;
    public int count;

    public StockJuice(Juice juice, int count) {
        this.juice = juice;
        this.count = count;
    }

    @Override
    public boolean equals(Object obj) {
        StockJuice stock = (StockJuice) obj;
        return juice.equals(stock.juice) && count == stock.count;
    }
}
