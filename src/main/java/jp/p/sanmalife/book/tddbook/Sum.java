package jp.p.sanmalife.book.tddbook;

public class Sum implements Expression {
    Expression augend; // 被加数
    Expression addend; // 加数

    public Sum(Expression augend, Expression added) {
        this.augend = augend;
        this.addend = added;
    }

    public Money reduce(Bank bank, String to) {
        int amount = addend.reduce(bank, to).amount
                + augend.reduce(bank, to).amount;
        return new Money(amount, to);
    }

    @Override
    public Expression plus(Expression addend) {
        // TODO
        return null;
    }

    @Override
    public Expression times(int multiplier) {
        // TODO
        return null;
    }
}
