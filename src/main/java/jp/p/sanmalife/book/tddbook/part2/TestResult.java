package jp.p.sanmalife.book.tddbook.part2;

public class TestResult {
    int runCount = 1;

    public String summary() {
        return String.format("%d run, 0 failed", runCount);
    }
}
