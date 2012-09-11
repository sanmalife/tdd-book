package jp.p.sanmalife.book.tddbook;

abstract class Money {

    protected int amount;

    @Override
    public boolean equals(Object obj) {
        Money money = (Money) obj;
        return amount == money.amount && getClass().equals(money.getClass());
    }

    public static Money dollar(int amount) {
        return new Dollar(amount);
    }

    abstract Money times(int multiplier);

}
