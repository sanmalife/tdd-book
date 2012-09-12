package jp.p.sanmalife.book.tddbook;

class Money implements Expression {

    protected int amount;
    protected String currency;

    public Money(int amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public Money times(int multiplier) {
        return new Money(amount * multiplier, currency);
    }

    public static Money dollar(int amount) {
        return new Money(amount, "USD");
    }

    public static Money franc(int amount) {
        return new Money(amount, "CHF");
    }

    protected String currency() {
        return currency;
    }

    @Override
    public boolean equals(Object obj) {
        Money money = (Money) obj;
        return amount == money.amount && currency.equals(money.currency);
    }

    @Override
    public String toString() {
        return amount + " " + currency;
    }

    public Expression plus(Money added) {
        return new Sum(this, added);
    }

    public Money reduce(String to) {
        return this;
    }

}
