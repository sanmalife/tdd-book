package jp.p.sanmalife.book.tddbook;

public class Franc extends Money {

    public Franc(int amount) {
        this.amount = amount;
    }

    @Override
    public Money times(int multiplier) {
        return new Franc(amount * multiplier);
    }

    @Override
    String currency() {
        return "CHF";
    }

}
