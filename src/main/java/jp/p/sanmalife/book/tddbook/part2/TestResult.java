package jp.p.sanmalife.book.tddbook.part2;

public class TestResult {
    int runCount = 0;
    int errorCount = 0;

    public void testStarted() {
        runCount++;
    }

    public void testFailed() {
        errorCount++;
    }

    public String summary() {
        return String.format("%d run, %d failed", runCount, errorCount);
    }
}
