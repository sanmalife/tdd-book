package jp.p.sanmalife.book.tddbook.tddbc;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class VendingMachineTest {
    private static VendingMachine vendingMachine;
    private static Juice coke;
    private static Juice redBull;
    private static Juice water;

    /**
     * 初期状態としてクラスの初期化
     */
    @BeforeClass
    public static void デフォルトジュースの生成() {
        coke = new Juice("コーラ", 120);
        redBull = new Juice("レッドブル", 200);
        water = new Juice("水", 100);
    }

    public static class 初期状態の場合 {
        @Before
        public void 初期化() {
            vendingMachine = new VendingMachine();
        }

        @Test
        public void 初期状態の時_投入金額の総計は0円() {
            assertThat(vendingMachine.getTotalAmount(), is(0));
        }

        @Test
        public void 初期状態の売上金額は0円() throws Exception {
            assertThat(vendingMachine.getSaleAmount(), is(0));
        }

        @Test
        public void 初期状態ではレッドブルと水とコーラとが5本ずつある在庫の情報を取得できる() throws Exception {
            Set<Stock> stocks = new HashSet<Stock>();
            stocks.add(new Stock(new Juice("レッドブル", 200), 5));
            stocks.add(new Stock(new Juice("水", 100), 5));
            stocks.add(new Stock(new Juice("コーラ", 120), 5));
            assertThat(vendingMachine.getStockSet(), is(stocks));
        }

        @Test
        public void 初期状態で払い戻し操作を行うとつり銭として空リストを返す() throws Exception {
            assertThat(vendingMachine.refund(), is(Collections.EMPTY_LIST));
        }

        @Test
        public void 初期状態で100円の水を1本買うと在庫が4本になる() throws Exception {
            vendingMachine.insert(100);
            vendingMachine.purchase(water);
            Stock waterStock = vendingMachine.getStock(water);
            assertThat(waterStock.count, is(4));
        }

        @Test
        public void 初期状態で100円の水を2本買うと在庫が3本になる() throws Exception {
            vendingMachine.insert(100);
            vendingMachine.purchase(water);
            vendingMachine.insert(100);
            vendingMachine.purchase(water);
            Stock waterStock = vendingMachine.getStock(water);
            assertThat(waterStock.count, is(3));
        }

        @Test
        public void 初期状態で100円の水を1本買うと売上金額が100円になる() throws Exception {
            vendingMachine.insert(100);
            vendingMachine.purchase(water);
            assertThat(vendingMachine.getSaleAmount(), is(100));
        }

        @Test
        public void 初期状態で100円の水を2本買うと売上金額が200円になる() throws Exception {
            vendingMachine.insert(100);
            vendingMachine.purchase(water);
            vendingMachine.insert(100);
            vendingMachine.purchase(water);
            assertThat(vendingMachine.getSaleAmount(), is(200));
        }

        @Test
        public void 初期状態で10円入れると投入金額の総計が10円になる() {
            vendingMachine.insert(10);
            assertThat(vendingMachine.getTotalAmount(), is(10));
        }

        @Test
        public void 初期状態で10円と100円を投入すると投入金額の総計が110円になる() {
            vendingMachine.insert(10);
            vendingMachine.insert(100);
            assertThat(vendingMachine.getTotalAmount(), is(110));
        }

        @Test
        public void 初期状態で10円を投入した後に払い戻し操作を連続で行うと2回目は空のリストが返る() throws Exception {
            vendingMachine.insert(10);
            vendingMachine.refund();
            vendingMachine.refund();
            assertThat(vendingMachine.refund(), is(Collections.EMPTY_LIST));
        }

        @Test
        public void 初期状態で10円と50円を投入した後で払い戻しを行うと10円と50円のリストを返す()
                throws Exception {
            vendingMachine.insert(10);
            vendingMachine.insert(50);
            assertThat(vendingMachine.refund(), is(Arrays.asList(10, 50)));
        }

        @Test
        public void 初期状態で10円を投入した後に払い戻し操作を行うと総計は0円になる() throws Exception {
            vendingMachine.insert(10);
            vendingMachine.refund();
            assertThat(vendingMachine.getTotalAmount(), is(0));
        }

        @Test
        public void 初期状態では釣り銭ストックとして有効なお金を10枚ずつ保持する() throws Exception {
            Map<Integer, Integer> initialChangeStock = vendingMachine
                    .getChangeStock();
            for (Integer money : VendingMachine.acceptMoneys) {
                assertThat(initialChangeStock.get(money), is(10));
            }
        }

        @Test
        public void 初期状態で500円を投入して100円の水を購入しても100円の釣り銭ストックが6枚になる()
                throws Exception {
            vendingMachine.insert(500);
            vendingMachine.purchase(water);
            Map<Integer, Integer> changeStock = vendingMachine.getChangeStock();
            assertThat(changeStock.get(100), is(6));
        }

        @Test
        public void 初期状態で110円を投入して100円の水を購入しても10円の釣り銭ストックは減らない()
                throws Exception {
            vendingMachine.insert(100);
            vendingMachine.insert(10);
            vendingMachine.purchase(water);

            Map<Integer, Integer> changeStock = vendingMachine.getChangeStock();
            assertThat(changeStock.get(10), is(10));
        }

        @Test
        public void 初期状態で210円投入して120円のコーラを購入するときに減る釣り銭ストックは10円3枚と50円1枚()
                throws Exception {
            vendingMachine.insert(100);
            vendingMachine.insert(100);
            vendingMachine.insert(10);
            vendingMachine.purchase(coke);
            Map<Integer, Integer> changeStock = vendingMachine.getChangeStock();

            assertThat(changeStock.get(50), is(9));
            assertThat(changeStock.get(10), is(7));
        }

        @Test
        public void 初期状態で110円を投入して払い戻しをしても釣り銭ストックは減らない() throws Exception {
            vendingMachine.insert(100);
            vendingMachine.insert(10);
            vendingMachine.refund();
            Map<Integer, Integer> changeStock = vendingMachine.getChangeStock();
            for (Integer coin : VendingMachine.acceptMoneys) {
                assertThat(changeStock.get(coin), is(10));
            }
        }
    }

    public static class ジュースを購入できない場合 {
        @Before
        public void 事前状態として水を購入できない状態にする() {
            vendingMachine = new VendingMachine();
        }

        @Test
        public void 購入できない場合に購入操作を行なっても投入金額が変化しない() throws Exception {
            vendingMachine.insert(10);
            vendingMachine.purchase(coke);
            assertThat(vendingMachine.getTotalAmount(), is(10));
        }

        @Test
        public void 購入できない場合に購入操作を行なっても在庫が変化しない() throws Exception {
            vendingMachine.purchase(coke);
            Stock cokeStock = vendingMachine.getStock(coke);
            assertThat(cokeStock.count, is(5));
        }

        @Test
        public void 購入できない場合に購入操作を行なっても売上が変化しない() throws Exception {
            vendingMachine.purchase(coke);
            assertThat(vendingMachine.getSaleAmount(), is(0));
        }

        @Test
        public void 投入金額が90円の時は購入可能なリストとして空のリストを取得できる() throws Exception {
            vendingMachine.insert(50);
            vendingMachine.insert(10);
            vendingMachine.insert(10);
            vendingMachine.insert(10);
            vendingMachine.insert(10);
            assertThat(vendingMachine.getPurchasableList(),
                    is(Collections.EMPTY_LIST));
        }

        @Test
        public void 投入金額が110円の場合はコーラを購入できない() throws Exception {
            vendingMachine.insert(100);
            vendingMachine.insert(10);
            assertThat(vendingMachine.getTotalAmount(), is(110));
            assertThat(vendingMachine.canPurchase(coke), is(false));
        }

        @Test
        public void 在庫が0の場合は投入金額が十分でも水を購入できない() throws Exception {
            assertThat(vendingMachine.getStock(water).count, is(5)); // 事前状態
            for (int i = 0; i < 5; i++) {
                vendingMachine.insert(100);
                vendingMachine.purchase(water);
            }
            Stock cokeStock = vendingMachine.getStock(water);
            assertThat(cokeStock.count, is(0));
            assertThat(vendingMachine.canPurchase(water), is(false));
        }

        @Test
        public void 投入金額が不足している場合に購入操作を行うと釣り銭として空リストを取得する() throws Exception {
            assertThat(vendingMachine.purchase(coke),
                    is(Collections.EMPTY_LIST));
        }
    }

    public static class ジュースとして水を購入できる場合 {
        @Before
        public void 事前状態として水を購入できる状態にする() {
            vendingMachine = new VendingMachine();
            vendingMachine.insert(100);
        }

        @Test
        public void 投入金額が100円の時に100円の水を購入すると投入金額が0円になる() throws Exception {
            vendingMachine.purchase(water);
            assertThat(vendingMachine.getTotalAmount(), is(0));
        }

        @Test
        public void 投入金額が100円の時は購入可能なリストとして水だけのリストが取得できる() throws Exception {
            assertThat(vendingMachine.getPurchasableList(),
                    is(Arrays.asList(water)));
        }

        @Test
        public void 投入金額が200円の時は購入可能なリストとしてコーラと水とレッドブルのリストが取得できる()
                throws Exception {
            vendingMachine.insert(100); // さらに100円追加

            // リストに含まれる要素が等しければ良い
            assertThat(new HashSet<Juice>(vendingMachine.getPurchasableList()),
                    is(new HashSet<Juice>(Arrays.asList(coke, redBull, water))));
        }

        @Test
        public void 投入金額が100円の場合は水を購入できる() throws Exception {
            assertThat(vendingMachine.getTotalAmount(), is(100));
            assertThat(vendingMachine.canPurchase(water), is(true));
        }

        @Test
        public void 値段が100円の水を買うと釣り銭として空リストを取得する() throws Exception {
            List<Integer> refund = vendingMachine.purchase(water);
            assertThat(refund, is(Collections.EMPTY_LIST));
        }

        @Test
        public void 投入金額より安い値段のジュースを買うと差額を釣り銭リストとして取得する() throws Exception {
            vendingMachine.insert(50);
            List<Integer> refund = vendingMachine.purchase(coke);
            int changeAmount = 0;
            for (Integer coin : refund) {
                changeAmount += coin;
            }

            assertThat(changeAmount, is(30));
        }
    }

    public static class 釣り銭が不足している場合 {

        int initialSaleAmount; // 初期状態の売上金額

        @Before
        public void 初期化として100円の釣り銭をなくす() {
            vendingMachine = new VendingMachine();

            // 380円のつり銭を2回と400円の釣り銭を1回
            for (int i = 0; i < 2; i++) {
                vendingMachine.insert(500);
                vendingMachine.purchase(coke);
            }
            // 100円4枚のつり銭1回と、50円8枚の釣り銭1回
            for (int i = 0; i < 2; i++) {
                vendingMachine.insert(500);
                vendingMachine.purchase(water);
            }

            initialSaleAmount = vendingMachine.getSaleAmount();

            Map<Integer, Integer> changeStock = vendingMachine.getChangeStock();
            assertThat(changeStock.get(100), is(0));
            assertThat(changeStock.get(50), is(0));
            assertThat(changeStock.get(10), is(4)); // 10円は残り40円分
        }

        @Test
        public void 釣り銭が不足しているときに購入しようとしても購入できない() throws Exception {
            vendingMachine.insert(500);
            assertThat(vendingMachine.canPurchase(water), is(false));
        }

        // 売上が変わらない
        // 投入金額が変わらない
        // 在庫が変わらない
        // 釣り銭が変わらない
    }

    public static class 常に成立する条件 {

        @Before
        public void 初期化() {
            vendingMachine = new VendingMachine();
        }

        @Test
        public void 想定内のお金が投入された場合はつり銭として0円を返す() throws Exception {
            for (Integer coin : VendingMachine.acceptMoneys) {
                assertThat(vendingMachine.insert(coin), is(0));
            }
        }

        @Test
        public void 扱えないお金を投入した場合は投入した金額をそのまま返す() throws Exception {
            int[] notAllowedMoneys = { 1, 5, 2000, 5000, 10000 };

            for (int i = 0; i < notAllowedMoneys.length; i++) {
                assertThat(vendingMachine.insert(notAllowedMoneys[i]),
                        is(notAllowedMoneys[i]));
            }
        }

        @Test
        public void 扱えないお金を投入した場合は投入金額の総計は増加しない() throws Exception {
            vendingMachine.insert(1);
            assertThat(vendingMachine.getTotalAmount(), is(0));
        }

        @Test
        public void Mapのequalsメソッドの振る舞いの確認() throws Exception {
            Map<String, String> map1 = new HashMap<String, String>();
            map1.put("a", "b");
            Map<String, String> map2 = new HashMap<String, String>();
            map2.put("a", "b");
            assertThat(map1.equals(map2), is(true));
        }

    }
}
