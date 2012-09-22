package jp.p.sanmalife.book.tddbook.tddbc;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class StockJuiceTest {

    @Test
    public void ジュースの種類と在庫数が一致していれば等しい() {
        Stock stock1 = new Stock(new Juice("コーラ", 120), 1);
        Stock stock2 = new Stock(new Juice("コーラ", 120), 1);
        assertThat(stock1, is(stock2));
    }

}
