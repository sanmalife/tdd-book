package jp.p.sanmalife.book.tddbook;

public class Bank {

    public Money reduce(Expression source, String to) {
        if (source instanceof Money) {
            return (Money) source;
        }

        Sum sum = (Sum) source;
        return sum.reduce(to);
    }

}
