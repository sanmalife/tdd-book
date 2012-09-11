package jp.p.sanmalife.book.tddbook;

class Money {

    protected int amount;
    protected String currency;

    public Money(int amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    @Override
    public boolean equals(Object obj) {
        Money money = (Money) obj;
        return amount == money.amount && currency.equals(money.currency);
    }

    Money times(int multiplier) {
        // TODO: 仮実装
        return null;
    }

    public static Money dollar(int amount) {
        return new Dollar(amount, "USD");
    }

    public static Money franc(int amount) {
        return new Franc(amount, "CHF");
    }

    protected String currency() {
        return currency;
    }

    @Override
    public String toString() {
        return amount + " " + currency;
    }

}
