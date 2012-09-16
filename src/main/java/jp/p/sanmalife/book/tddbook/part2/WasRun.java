package jp.p.sanmalife.book.tddbook.part2;

public class WasRun {

    public boolean wasRun;

    public WasRun(String string) {
        wasRun = false;
    }

    public void testMethod() {
        wasRun = true;
    }

    public void run() {
        testMethod();
    }

}
