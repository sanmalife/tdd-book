package jp.p.sanmalife.book.tddbook.tddbc;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class JuiceTest {

    @Test
    public void 名前と値段が一致していれば等しい() {
        Juice coke1 = new Juice("コーラ", 120);
        Juice coke2 = new Juice("コーラ", 120);
        assertEquals(coke1, coke2);
    }
}
