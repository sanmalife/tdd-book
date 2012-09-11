package jp.p.sanmalife.book.tddbook;

public class Dollar extends Money {

    public Dollar(int amount, String currency) {
        super(amount, currency);
    }

    @Override
    public Money times(int multiplier) {
        return new Money(amount * multiplier, currency);
    }

}
