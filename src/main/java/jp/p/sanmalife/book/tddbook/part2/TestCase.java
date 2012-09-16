package jp.p.sanmalife.book.tddbook.part2;

import java.lang.reflect.InvocationTargetException;
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
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
