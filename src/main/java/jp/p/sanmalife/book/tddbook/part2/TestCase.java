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

    public void run() {
        Class clzz = this.getClass();
        Method method;
        try {
            method = clzz.getMethod(name, null);
            method.invoke(this, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
