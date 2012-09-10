package jp.p.sanmalife.book.tddbook;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FrancTest {

    @Test
    public void testFrancMultiplication() throws Exception {
        Franc five = new Franc(5);
        assertEquals(new Franc(10), five.times(2));
        assertEquals(new Franc(15), five.times(3));
    }
}
