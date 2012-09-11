package jp.p.sanmalife.book.tddbook;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class DollarTest {
    @Test
    public void testMultiplication() throws Exception {
        Money five = Money.dollar(5);
        assertEquals(Money.dollar(10), five.times(2));
        assertEquals(Money.dollar(15), five.times(3));
    }

    @Test
    public void testEquality() throws Exception {
        assertTrue(Money.dollar(5).equals(new Dollar(5)));
        assertFalse(Money.dollar(5).equals(new Dollar(6)));
        assertFalse(Money.franc(5).equals(Money.dollar(5)));
    }

}