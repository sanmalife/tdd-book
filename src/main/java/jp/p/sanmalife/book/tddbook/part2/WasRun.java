package jp.p.sanmalife.book.tddbook.part2;

import java.lang.reflect.Method;

public class WasRun {

    public boolean wasRun;
    private String name;

    public WasRun(String name) {
        this.name = name;
        wasRun = false;
    }

    public void testMethod() {
        wasRun = true;
    }

    public void run() {
        try {
            Class clzz = this.getClass();
            Method method = clzz.getMethod(name, null);
            method.invoke(this, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
