package jp.p.sanmalife.book.tddbook.part2;

import static org.junit.Assert.assertTrue;

/**
 * TestCaseでテストが呼び出されることを確認するテスト
 * 
 * @author kozai.takeshi
 * 
 */
public class TestCaseTest extends TestCase {
    private WasRun test;

    public TestCaseTest(String name) {
        super(name);
    }

    @Override
    public void setUp() {
        test = new WasRun("testMethod");
    }

    public void testRunning() {
        test.run();
        assertTrue(test.wasRun);
    }

    public void testSetUp() {
        test.run();
        assertTrue(test.wasSetUp);
    }

    public static void main(String[] args) {
        new TestCaseTest("testRunning").run();
        new TestCaseTest("testSetUp").run();
        System.out.println("Test End.");
    }

}
