package jp.p.sanmalife.book.tddbook.tddbc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
     * 売上金額
     */
    private int saleAmount;

    private HashMap<Juice, Stock> stocks = new HashMap<Juice, Stock>();

    public VendingMachine() {
        storeJuice(new Stock(new Juice("レッドブル", 200), 5));
        storeJuice(new Stock(new Juice("水", 100), 5));
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
     * 格納しているジュースを返す
     * 
     * @return
     */
    public Stock getStock(Juice juice) {
        return stocks.get(juice);
    }

    public Set<Stock> getStockSet() {
        return new HashSet<Stock>(stocks.values());
    }

    /**
     * ジュースを格納する
     * 
     * @param type
     */
    public void storeJuice(Stock stock) {
        stocks.put(stock.type, stock);
    }

    /**
     * ジュースを購入できるか判定する。在庫が1以上かつ、投入金額総計が値段以上の場合購入可能。
     * 
     * @param juice
     *            判定対象ジュース
     * 
     * @return ジュースが購入できる場合true
     */
    public boolean canPurchase(Juice juice) {
        return (getTotalAmount() >= juice.getPrice())
                && (getStock(juice).count > 0) ? true : false;
    }

    /**
     * ジュースを購入する
     * 
     * @param juice
     */
    public void purchase(Juice juice) {
        if (!canPurchase(juice)) {
            return;
        }

        Stock stock = getStock(juice);
        stock.count -= 1;
        saleAmount += juice.getPrice();

        // 投入金額更新処理
        int preAmount = getTotalAmount();
        int postAmount = preAmount - juice.getPrice();
        insertedMoney = getCoins(postAmount);
    }

    /**
     * 現在の売上金額を取得する
     * 
     * @return 売上金額
     */
    public int getSaleAmount() {
        return saleAmount;
    }

    /**
     * 指定された金額を表現する小銭リストを計算する
     * 
     * @param amount
     *            金額
     * @return 金額を表現する小銭リスト
     */
    private ArrayList<Integer> getCoins(int amount) {
        ArrayList<Integer> coins = new ArrayList<Integer>();

        int len = acceptMoneys.size();
        for (int i = len - 1; i >= 0; i--) {
            int coin = acceptMoneys.get(i);
            while (amount >= coin) {
                coins.add(coin);
                amount -= coin;
            }
        }
        return coins;
    }
}
