package jp.p.sanmalife.book.tddbook.part2;


public class WasRun extends TestCase {

    public boolean wasRun;

    public WasRun(String name) {
        super(name);
        wasRun = false;
    }

    public void testMethod() {
        wasRun = true;
    }

}
