package jp.p.sanmalife.book.tddbook;

public class Bank {

    public Money reduce(Expression source, String to) {
        Sum sum = (Sum) source;
        int amount = sum.addend.amount + sum.augend.amount;
        return Money.dollar(amount);
    }

}
