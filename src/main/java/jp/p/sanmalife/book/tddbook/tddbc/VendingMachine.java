package jp.p.sanmalife.book.tddbook.tddbc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
    protected static final List<Integer> acceptMoneys = Arrays.asList(10, 50,
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

    private HashMap<Integer, Integer> changeStock = new HashMap<Integer, Integer>();

    public VendingMachine() {
        store(new Stock(new Juice("レッドブル", 200), 5));
        store(new Stock(new Juice("水", 100), 5));
        store(new Stock(new Juice("コーラ", 120), 5));

        // 初期状態では各10枚保持する
        for (Integer money : acceptMoneys) {
            setChangeStock(money, 10);
        }
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
        clear();
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
    public void store(Stock stock) {
        stocks.put(stock.type, stock);
    }

    /**
     * ジュースを購入できるか判定する。在庫が1以上かつ、投入金額総計が値段以上で、お釣りが支払える場合購入可能。
     * 
     * @param juice
     *            判定対象ジュース
     * 
     * @return ジュースが購入できる場合true
     */
    public boolean canPurchase(Juice juice) {
        // お釣りに関して
        Map<Integer, Integer> coins = new HashMap<Integer, Integer>();
        for (Integer coin : changeStock.keySet()) {
            coins.put(coin, changeStock.get(coin));
        }
        for (Integer coin : insertedMoney) {
            coins.put(coin, coins.get(coin) + 1);
        }
        int len = acceptMoneys.size();
        int change = getTotalAmount() - juice.getPrice();
        for (int i = len - 1; i >= 0; i--) {
            Integer coin = acceptMoneys.get(i);
            while (change >= coin) {
                if (coins.get(coin) == 0) {
                    break;
                }
                change -= coin;
                coins.put(coin, coins.get(coin) - 1);
            }
        }
        if (change != 0) {
            return false;
        }

        // 在庫と投入金額に関して
        return (getTotalAmount() >= juice.getPrice())
                && (getStock(juice).count > 0) ? true : false;
    }

    /**
     * ジュースを購入する
     * 
     * @param juice
     *            購入するジュース
     * @return 釣り銭リスト
     */
    public List<Integer> purchase(Juice juice) {
        if (!canPurchase(juice)) {
            return Collections.emptyList();
        }

        // 在庫
        Stock stock = getStock(juice);
        stock.count -= 1;

        // 売上
        saleAmount += juice.getPrice();

        // 釣り銭
        int change = getTotalAmount() - juice.getPrice();
        List<Integer> changeCoins = getChangeCoins(change);

        clear();

        return changeCoins;
    }

    /**
     * 初期状態にする
     */
    private void clear() {
        insertedMoney = new ArrayList<Integer>();
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
     * 現在の投入金額で購入可能なジュースのリストを返す
     * 
     * @return 購入可能なジュースのリスト
     */
    public List<Juice> getPurchasableList() {
        List<Juice> purchasables = new ArrayList<Juice>();
        for (Stock stock : getStockSet()) {
            if (canPurchase(stock.type)) {
                purchasables.add(stock.type);
            }
        }

        return purchasables;
    }

    /**
     * 指定された金額を表現する釣り銭リストを取得する。釣り銭には投入されたお金を優先して利用する。
     * 
     * @param change
     *            釣り銭の金額
     * @return 金額を表現する小銭リスト
     */
    private ArrayList<Integer> getChangeCoins(int change) {
        ArrayList<Integer> changeCoins = new ArrayList<Integer>();

        // 額面の大きなお金から優先して利用する
        int len = acceptMoneys.size();
        for (int i = len - 1; i >= 0; i--) {
            int coin = acceptMoneys.get(i);
            while (change >= coin) {
                if (insertedMoney.contains(Integer.valueOf(coin))) {
                    insertedMoney.remove(Integer.valueOf(coin));
                    changeCoins.add(coin);
                    change -= coin;
                } else if (changeStock.get(coin) != 0) {
                    changeStock.put(coin, changeStock.get(coin) - 1);
                    changeCoins.add(coin);
                    change -= coin;
                } else {
                    break;
                }
            }
        }
        return changeCoins;
    }

    /**
     * 釣り銭ストックを取得する
     * 
     * @return 釣り銭ストック
     */
    public Map<Integer, Integer> getChangeStock() {
        return changeStock;
    }

    /**
     * 釣り銭ストックを設定する
     * 
     * @param money
     *            金額
     * @param count
     *            枚数
     */
    private void setChangeStock(Integer money, Integer count) {
        changeStock.put(money, count);
    }
}
