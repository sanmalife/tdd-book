package jp.p.sanmalife.book.tddbook.tddbc;

/**
 * 自動販売機クラス
 * 
 * @author kozai.takeshi
 * 
 */
public class VendingMachine {

    /**
     * 投入金額の総計
     */
    public int totalAmount;

    /**
     * お金を投入する
     * 
     * @param money
     *            投入する金額
     */
    public void insert(int money) {
        totalAmount += money;
    }

}
