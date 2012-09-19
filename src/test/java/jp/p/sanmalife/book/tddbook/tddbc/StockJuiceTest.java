package jp.p.sanmalife.book.tddbook.tddbc;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class StockJuiceTest {

    @Test
    public void ジュースの種類と在庫数が一致していれば等しい() {
        Stock stock1 = new Stock(new Juice("コーラ", 120), 1);
        Stock stock2 = new Stock(new Juice("コーラ", 120), 1);
        assertTrue(stock1.equals(stock2));
    }

}
