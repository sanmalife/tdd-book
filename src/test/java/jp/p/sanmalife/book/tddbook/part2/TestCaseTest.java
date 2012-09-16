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
        assertEquals("setUp testMethod ", test.log);
    }

    public static void main(String[] args) {
        new TestCaseTest("testTemplateMethod").run();
        System.out.println("Test End.");
    }

}
