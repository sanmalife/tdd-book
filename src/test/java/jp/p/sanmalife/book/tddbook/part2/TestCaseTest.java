package jp.p.sanmalife.book.tddbook.part2;

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
        assert (!test.wasRun);
        test.run();
        assert (test.wasRun);
    }

    public static void main(String[] args) {
        new TestCaseTest("testRunning").run();
    }

}
