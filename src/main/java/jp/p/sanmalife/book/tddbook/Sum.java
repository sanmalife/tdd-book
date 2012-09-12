package jp.p.sanmalife.book.tddbook;

public class Sum implements Expression {
    public Money augend;
    public Money addend;

    public Sum(Money augend, Money added) {
        this.augend = augend;
        this.addend = added;
    }

}
