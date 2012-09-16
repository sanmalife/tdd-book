package jp.p.sanmalife.book.tddbook.part2;

/**
 * テストが確かに実行されたことを確認するためのテストケース
 * 
 * @author kozai.takeshi
 * 
 */
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
