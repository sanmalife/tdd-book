package jp.p.sanmalife.book.tddbook;

public class Franc extends Money {

    private String currency;

    public Franc(int amount) {
        this.amount = amount;
        currency = "CHF";
    }

    @Override
    public Money times(int multiplier) {
        return new Franc(amount * multiplier);
    }

    @Override
    String currency() {
        return currency;
    }

}
