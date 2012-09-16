package jp.p.sanmalife.book.tddbook.part2;

import java.util.ArrayList;
import java.util.List;

public class TestSuite {

    List<TestCase> tests = new ArrayList<TestCase>();

    public void add(TestCase test) {
        tests.add(test);
    }

    public TestResult run(TestResult result) {
        for (TestCase test : tests) {
            test.run(result);
        }
        return result;
    }

}
