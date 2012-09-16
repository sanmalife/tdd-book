package jp.p.sanmalife.book.tddbook.part2;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class WasRunTest {

    @Test
    public void testTestMethod() {
        WasRun test = new WasRun("testMethod");
        assertEquals(false, test.wasRun);
        test.run(new TestResult());
        assertEquals(true, test.wasRun);
    }

}
