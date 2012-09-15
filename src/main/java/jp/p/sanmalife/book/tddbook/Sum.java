package jp.p.sanmalife.book.tddbook;

public class Sum implements Expression {
    Money augend; // 被加数
    Money addend; // 加数

    public Sum(Money augend, Money added) {
        this.augend = augend;
        this.addend = added;
    }

    public Money reduce(Bank bank, String to) {
        int amount = addend.reduce(bank, to).amount
                + augend.reduce(bank, to).amount;
        return new Money(amount, to);
    }
}
