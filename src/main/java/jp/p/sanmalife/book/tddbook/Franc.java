package jp.p.sanmalife.book.tddbook;

public class Franc extends Money {

    public Franc(int amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    @Override
    public Money times(int multiplier) {
        return new Franc(amount * multiplier, null);
    }

}
