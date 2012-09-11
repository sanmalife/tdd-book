package jp.p.sanmalife.book.tddbook;

abstract class Money {

    protected int amount;
    protected String currency;

    @Override
    public boolean equals(Object obj) {
        Money money = (Money) obj;
        return amount == money.amount && getClass().equals(money.getClass());
    }

    abstract Money times(int multiplier);

    public static Money dollar(int amount) {
        return new Dollar(amount, "USD");
    }

    public static Money franc(int amount) {
        return new Franc(amount, "CHF");
    }

    protected String currency() {
        return currency;
    }

}
