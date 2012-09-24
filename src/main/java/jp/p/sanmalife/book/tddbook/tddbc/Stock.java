package jp.p.sanmalife.book.tddbook.tddbc;

public class Stock {
    public Juice type;
    public int count;

    public Stock(Juice juice, int count) {
        this.type = juice;
        this.count = count;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!(obj instanceof Stock)) {
            return false;
        }

        Stock stock = (Stock) obj;
        return type.equals(stock.type) && count == stock.count;
    }

    @Override
    public String toString() {
        return "{Juice: {" + type.toString() + "}, Count: " + count + "}";
    }

    @Override
    public int hashCode() {
        return type.hashCode() + 31 * count;
    }
}
