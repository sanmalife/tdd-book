package jp.p.sanmalife.book.tddbook.tddbc;

import java.util.ArrayList;
import java.util.List;

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
    private ArrayList<Integer> insertedMoney = new ArrayList<Integer>();

    /**
     * お金を投入する
     * 
     * @param money
     *            投入する金額
     */
    public void insert(int money) {
        totalAmount += money;
        insertedMoney.add(money);
    }

    /**
     * 払い戻し処理
     * 
     * @return つり銭
     */
    public List<Integer> refund() {
        totalAmount = 0;
        return insertedMoney;
    }

}
