package jp.p.sanmalife.book.tddbook;

public class Franc extends Money {

    public Franc(int amount) {
        this.amount = amount;
        currency = "CHF";
    }

    @Override
    public Money times(int multiplier) {
        return new Franc(amount * multiplier);
    }

}
