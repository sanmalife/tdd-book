package jp.p.sanmalife.book.tddbook;

public interface Expression {
    Money reduce(Bank bank, String to);
}
