package jp.p.sanmalife.book.tddbook;

public class Bank {

    public Money reduce(Expression source, String currency) {
        Sum sum = (Sum) source;
        int amount = sum.addend.amount + sum.augend.amount;
        return new Money(amount, currency);
    }
}
