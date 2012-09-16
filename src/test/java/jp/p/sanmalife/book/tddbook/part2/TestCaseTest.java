package jp.p.sanmalife.book.tddbook.part2;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * TestCaseでテストが呼び出されることを確認するテスト
 * 
 * @author kozai.takeshi
 * 
 */
public class TestCaseTest extends TestCase {

    public TestCaseTest(String name) {
        super(name);
    }

    public void testRunning() {
        WasRun test = new WasRun("testMethod");
        assertFalse(test.wasRun);
        test.run();
        assertTrue(test.wasRun);
    }

    public void testSetUp() {
        WasRun test = new WasRun("testMethod");
        test.run();
        assertTrue(test.wasSetUp);
    }

    public static void main(String[] args) {
        new TestCaseTest("testRunning").run();
        new TestCaseTest("testSetUp").run();
    }

}
