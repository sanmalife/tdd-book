package jp.p.sanmalife.book.tddbook.tddbc;

import static org.junit.Assert.assertEquals;

import java.util.Collections;

import org.junit.Before;
import org.junit.Test;

public class VendingMachineTest {

    private VendingMachine vendingMachine;

    @Test
    public void 初期状態の時_投入金額の総計は0円() {
        assertEquals(0, vendingMachine.totalAmount);
    }

    @Before
    public void before() {
        vendingMachine = new VendingMachine();
    }

    @Test
    public void お金を1回投入すると投入金額の総計が増加する() {
        vendingMachine.insert(10);
        assertEquals(10, vendingMachine.totalAmount);
    }

    @Test
    public void お金を2回投入すると投入金額の総計は2回の投入金額の合計になる() {
        vendingMachine.insert(10);
        vendingMachine.insert(50);
        assertEquals(60, vendingMachine.totalAmount);
    }

    @Test
    public void 初期状態で払い戻し操作を行うとつり銭として空リストを返す() throws Exception {
        assertEquals(Collections.EMPTY_LIST, vendingMachine.refund());
    }
}
