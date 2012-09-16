package jp.p.sanmalife.book.tddbook.part2;

import static org.junit.Assert.assertEquals;

/**
 * TestCaseでテストが呼び出されることを確認するテスト
 * 
 * @author kozai.takeshi
 * 
 */
public class TestCaseTest extends TestCase {

    private TestResult result;

    public TestCaseTest(String name) {
        super(name);
    }

    @Override
    public void setUp() {
        result = new TestResult();
    }

    public void testTemplateMethod() {
        WasRun test = new WasRun("testMethod");
        test.run(result);
        assertEquals("setUp testMethod tearDown ", test.log);
    }

    public void testResult() {
        WasRun test = new WasRun("testMethod");
        test.run(result);
        assertEquals("1 run, 0 failed", result.summary());
    }

    public void testFailedResult() {
        WasRun test = new WasRun("testBrokenMethod");
        test.run(result);
        assertEquals("1 run, 1 failed", result.summary());
    }

    public void testFailedResultFormatting() {
        result.testStarted();
        result.testFailed();
        assertEquals("1 run, 1 failed", result.summary());
    }

    public void testSuite() {
        TestSuite suite = new TestSuite();
        suite.add(new WasRun("testMethod"));
        suite.add(new WasRun("testBrokenMethod"));
        suite.run(result);
        assertEquals("2 run, 1 failed", result.summary());
    }

    public static void main(String[] args) throws Exception {
        TestSuite suite = new TestSuite();
        suite.add(new TestCaseTest("testTemplateMethod"));
        suite.add(new TestCaseTest("testResult"));
        suite.add(new TestCaseTest("testFailedResult"));
        suite.add(new TestCaseTest("testFailedResultFormatting"));
        suite.add(new TestCaseTest("testSuite"));
        TestResult result = new TestResult();
        suite.run(result);
        System.out.println(result.summary());
    }

}
