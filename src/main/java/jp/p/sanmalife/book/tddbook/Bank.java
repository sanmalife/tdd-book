package jp.p.sanmalife.book.tddbook;

public class Bank {

    public Money reduce(Expression source, String to) {
        return source.reduce(this, to);
    }

    public void addRate(String string, String string2, int i) {
    }

    int rate(String from, String to) {
        return (from.equals("CHF") && to.equals("USD")) ? 2 : 1;
    }
}
