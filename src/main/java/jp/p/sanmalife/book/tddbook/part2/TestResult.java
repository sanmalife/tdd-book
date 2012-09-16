package jp.p.sanmalife.book.tddbook.part2;

public class TestResult {
    int runCount = 0;

    public void testStarted() {
        runCount++;
    }

    public void testFailed() {
    }

    public String summary() {
        return String.format("%d run, 0 failed", runCount);
    }
}
