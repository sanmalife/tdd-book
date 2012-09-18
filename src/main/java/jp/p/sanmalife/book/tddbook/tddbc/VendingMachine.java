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
     * 投入されたお金のリスト
     */
    private ArrayList<Integer> insertedMoney = new ArrayList<Integer>();

    /**
     * お金を投入する
     * 
     * @param money
     *            投入する金額
     */
    public void insert(int money) {
        insertedMoney.add(money);
    }

    /**
     * 払い戻し処理
     * 
     * @return つり銭
     */
    public List<Integer> refund() {
        ArrayList<Integer> changeList = insertedMoney;
        insertedMoney = new ArrayList<Integer>();
        return changeList;
    }

    public int getTotalAmount() {
        int amount = 0;
        for (Integer money : insertedMoney) {
            amount += money;
        }
        return amount;
    }

}
