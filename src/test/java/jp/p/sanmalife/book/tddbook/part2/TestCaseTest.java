package jp.p.sanmalife.book.tddbook.part2;

import static org.junit.Assert.assertEquals;

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

    @Override
    public void setUp() {
    }

    public void testTemplateMethod() {
        WasRun test = new WasRun("testMethod");
        test.run();
        assertEquals("setUp testMethod tearDown ", test.log);
    }

    public void testResult() {
        WasRun test = new WasRun("testMethod");
        TestResult result = test.run();
        assertEquals("1 run, 0 failed", result.summary());
    }

    public void testFailedResult() {
        WasRun test = new WasRun("testBrokenMethod");
        TestResult result = test.run();
        assertEquals("1 run, 1 failed", result.summary());
    }

    public void testFailedResultFormatting() {
        TestResult result = new TestResult();
        result.testStarted();
        result.testFailed();
        assertEquals("1 run, 1 failed", result.summary());
    }

    public static void main(String[] args) throws Exception {
        new TestCaseTest("testTemplateMethod").run();
        new TestCaseTest("testFailedResult").run();
        new TestCaseTest("testFailedResultFormatting").run();
        System.out.println("Test End.");
    }

}
