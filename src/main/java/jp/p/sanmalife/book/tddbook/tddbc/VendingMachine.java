package jp.p.sanmalife.book.tddbook.tddbc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 自動販売機クラス
 * 
 * @author kozai.takeshi
 * 
 */
public class VendingMachine {

    /**
     * 扱えるお金の金額リスト
     */
    private static final List<Integer> acceptMoneys = Arrays.asList(10, 50,
            100, 500, 1000);

    /**
     * 投入されたお金のリスト
     */
    private ArrayList<Integer> insertedMoney = new ArrayList<Integer>();

    /**
     * 格納しているジュース
     */
    private Stock stockJuice;

    public VendingMachine() {
        storeJuice(new Stock(new Juice("コーラ", 120), 5));
    }

    /**
     * お金を投入する
     * 
     * @param money
     *            投入する金額
     * @return つり銭。取り扱い可能なお金の場合は0。そうでない場合は投入金額を返す
     */
    public int insert(int money) {
        if (!acceptMoneys.contains(money)) {
            return money;
        }

        insertedMoney.add(money);
        return 0;
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

    /**
     * 投入金額の総計を取得する
     * 
     * @return 投入金額の総計
     */
    public int getTotalAmount() {
        int amount = 0;
        for (Integer money : insertedMoney) {
            amount += money;
        }
        return amount;
    }

    /**
     * 格納しているジュースの本数を取得する
     * 
     * @return 格納しているジュースの本数
     */
    public int getStockCount() {
        return stockJuice.count;
    }

    /**
     * 格納しているジュースを返す
     * 
     * @return
     */
    public Stock getStock() {
        return stockJuice;
    }

    /**
     * ジュースを格納する
     * 
     * @param type
     */
    public void storeJuice(Stock stock) {
        stockJuice = stock;
    }
}
