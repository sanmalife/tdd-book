package jp.p.sanmalife.book.tddbook.part2;

import java.lang.reflect.Method;

/**
 * テストケース。テストドライバ
 * 
 * @author kozai.takeshi
 * 
 */
public class TestCase {
    private String name;

    public TestCase(String name) {
        this.name = name;
    }

    public void setUp() {
    }

    public void tearDown() {
    }

    public TestResult run() {
        Class clzz = this.getClass();
        Method method;
        TestResult result = new TestResult();
        result.testStarted();
        setUp();
        try {
            method = clzz.getMethod(name, null);
            method.invoke(this, null);
            tearDown();
        } catch (Exception e) {
            result.testFailed();
            e.printStackTrace();
        }

        return result;
    }
}
