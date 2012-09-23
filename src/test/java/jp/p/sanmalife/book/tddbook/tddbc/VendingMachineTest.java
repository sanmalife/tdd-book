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
import org.junit.Test;

public class VendingMachineTest {

    private VendingMachine vendingMachine;
    private Juice coke;
    private Juice redBull;
    private Juice water;

    @Before
    public void before() {
        vendingMachine = new VendingMachine();
        coke = new Juice("コーラ", 120);
        redBull = new Juice("レッドブル", 200);
        water = new Juice("水", 100);
    }

    @Test
    public void 初期状態の時_投入金額の総計は0円() {
        assertThat(vendingMachine.getTotalAmount(), is(0));
    }

    @Test
    public void お金を1回投入すると投入金額の総計が増加する() {
        vendingMachine.insert(10);
        assertThat(vendingMachine.getTotalAmount(), is(10));
    }

    @Test
    public void お金を2回投入すると投入金額の総計は2回の投入金額の合計になる() {
        vendingMachine.insert(10);
        vendingMachine.insert(50);
        assertThat(vendingMachine.getTotalAmount(), is(60));
    }

    @Test
    public void 初期状態で払い戻し操作を行うとつり銭として空リストを返す() throws Exception {
        assertThat(vendingMachine.refund(), is(Collections.EMPTY_LIST));
    }

    @Test
    public void 払い戻し操作を連続で行うと2回目は空のリストが返る() throws Exception {
        vendingMachine.insert(10);
        vendingMachine.refund();
        vendingMachine.refund();
        assertThat(vendingMachine.refund(), is(Collections.EMPTY_LIST));
    }

    @Test
    public void 複数回お金を投入した状態で払い戻し操作を行うと投入したお金のリストを返す() throws Exception {
        vendingMachine.insert(10);
        vendingMachine.insert(50);
        assertThat(vendingMachine.refund(), is(Arrays.asList(10, 50)));
    }

    @Test
    public void 払い戻し操作を行うと総計は0円になる() throws Exception {
        vendingMachine.insert(10);
        vendingMachine.refund();
        assertThat(vendingMachine.getTotalAmount(), is(0));
    }

    @Test
    public void 想定内のお金が投入された場合はつり銭として0円を返す() throws Exception {
        assertThat(vendingMachine.insert(10), is(0));
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

    @Test
    public void 投入金額が110円の場合はコーラを購入できない() throws Exception {
        vendingMachine.insert(100);
        vendingMachine.insert(10);
        assertThat(vendingMachine.getTotalAmount(), is(110));
        assertThat(vendingMachine.canPurchase(coke), is(false));
    }

    @Test
    public void 投入金額が120円の場合はコーラを購入できる() throws Exception {
        vendingMachine.insert(100);
        vendingMachine.insert(10);
        vendingMachine.insert(10);
        assertThat(vendingMachine.getTotalAmount(), is(120));
        assertThat(vendingMachine.canPurchase(coke), is(true));
    }

    @Test
    public void 初期状態の売上金額は0円() throws Exception {
        assertThat(vendingMachine.getSaleAmount(), is(0));
    }

    @Test
    public void 在庫が0の場合は投入金額が十分でもコーラを購入できない() throws Exception {
        vendingMachine.insert(1000);
        for (int i = 0; i < 5; i++) {
            vendingMachine.purchase(coke);
        }
        Stock cokeStock = vendingMachine.getStock(coke);
        assertThat(cokeStock.count, is(0));
        assertThat(vendingMachine.canPurchase(coke), is(false));
    }

    @Test
    public void 初期状態でコーラを1本買うと在庫が4本になる() throws Exception {
        vendingMachine.insert(500);
        vendingMachine.purchase(coke);
        Stock cokeStock = vendingMachine.getStock(coke);
        assertThat(cokeStock.count, is(4));
    }

    @Test
    public void 初期状態でコーラを1本買うと売上金額が120円になる() throws Exception {
        vendingMachine.insert(500);
        vendingMachine.purchase(coke);
        assertThat(vendingMachine.getSaleAmount(), is(120));
    }

    @Test
    public void 初期状態でコーラを2本買うと在庫が3本になる() throws Exception {
        vendingMachine.insert(500);
        vendingMachine.purchase(coke);
        vendingMachine.purchase(coke);
        Stock cokeStock = vendingMachine.getStock(coke);
        assertThat(cokeStock.count, is(3));
    }

    @Test
    public void 初期状態でコーラを2本買うと売上金額が240円になる() throws Exception {
        vendingMachine.insert(500);
        vendingMachine.purchase(coke);
        vendingMachine.purchase(coke);
        assertThat(vendingMachine.getSaleAmount(), is(240));
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
    public void 投入金額が120円の時に120円のコーラを購入すると投入金額が0円になる() throws Exception {
        vendingMachine.insert(100);
        vendingMachine.insert(10);
        vendingMachine.insert(10);
        vendingMachine.purchase(coke);
        assertThat(vendingMachine.getTotalAmount(), is(0));
    }

    @Test
    public void 投入金額が500円の時に120円のコーラを購入すると投入金額が380円になる() throws Exception {
        vendingMachine.insert(500);
        vendingMachine.purchase(coke);
        assertThat(vendingMachine.getTotalAmount(), is(380));
    }

    @Test
    public void 投入金額が500円の時に120円のコーラを購入して払い戻しをすると380円のお釣りが返る() throws Exception {
        vendingMachine.insert(500);
        vendingMachine.purchase(coke);
        List<Integer> coins = vendingMachine.refund();
        int change = 0;
        for (Integer coin : coins) {
            change += coin;
        }

        assertThat(change, is(380));
    }

    @Test
    public void 初期状態ではレッドブルと水とコーラとが5本ずつある在庫の情報を取得できる() throws Exception {
        Set<Stock> stocks = new HashSet<Stock>();
        stocks.add(new Stock(new Juice("レッドブル", 200), 5));
        stocks.add(new Stock(new Juice("水", 100), 5));
        stocks.add(new Stock(new Juice("コーラ", 120), 5));
        assertThat(vendingMachine.getStockSet(), is(stocks));
    }
}
