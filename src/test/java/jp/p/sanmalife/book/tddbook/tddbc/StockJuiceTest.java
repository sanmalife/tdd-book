package jp.p.sanmalife.book.tddbook.tddbc;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class StockJuiceTest {

    @Test
    public void ジュースの種類と在庫数が一致していれば等しい() {
        StockJuice stock1 = new StockJuice(new Juice("コーラ", 120), 1);
        StockJuice stock2 = new StockJuice(new Juice("コーラ", 120), 1);
        assertTrue(stock1.equals(stock2));
    }

}
