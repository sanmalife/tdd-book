package jp.p.sanmalife.book.tddbook.part2;

import static org.junit.Assert.assertEquals;

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

    public void testTemplateMethod() {
        test.run();
        assertEquals("setUp testMethod ", test.log);
    }

    public static void main(String[] args) {
        new TestCaseTest("testRunning").run();
        new TestCaseTest("testSetUp").run();
        System.out.println("Test End.");
    }

}
