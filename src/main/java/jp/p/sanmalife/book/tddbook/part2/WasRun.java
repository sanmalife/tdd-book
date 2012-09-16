package jp.p.sanmalife.book.tddbook.part2;

/**
 * テストが確かに実行されたことを確認するためのテストケース
 * 
 * @author kozai.takeshi
 * 
 */
public class WasRun extends TestCase {

    public boolean wasRun = false;
    public boolean wasSetUp = false;

    public WasRun(String name) {
        super(name);
    }

    public void setUp() {
        wasSetUp = true;
    }

    public void testMethod() {
        wasRun = true;
    }

}
