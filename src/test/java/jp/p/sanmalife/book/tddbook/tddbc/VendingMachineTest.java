package jp.p.sanmalife.book.tddbook.tddbc;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Before;
import org.junit.Test;

public class VendingMachineTest {

    private VendingMachine vendingMachine;

    @Before
    public void before() {
        vendingMachine = new VendingMachine();
    }

    @Test
    public void 初期状態の時_投入金額の総計は0円() {
        assertEquals(0, vendingMachine.getTotalAmount());
    }

    @Test
    public void お金を1回投入すると投入金額の総計が増加する() {
        vendingMachine.insert(10);
        assertEquals(10, vendingMachine.getTotalAmount());
    }

    @Test
    public void お金を2回投入すると投入金額の総計は2回の投入金額の合計になる() {
        vendingMachine.insert(10);
        vendingMachine.insert(50);
        assertEquals(60, vendingMachine.getTotalAmount());
    }

    @Test
    public void 初期状態で払い戻し操作を行うとつり銭として空リストを返す() throws Exception {
        assertEquals(Collections.EMPTY_LIST, vendingMachine.refund());
    }

    @Test
    public void 払い戻し操作を連続で行うと2回目は空のリストが返る() throws Exception {
        vendingMachine.insert(10);
        vendingMachine.refund();
        vendingMachine.refund();
        assertEquals(Collections.EMPTY_LIST, vendingMachine.refund());
    }

    @Test
    public void 複数回お金を投入した状態で払い戻し操作を行うと投入したお金のリストを返す() throws Exception {
        vendingMachine.insert(10);
        vendingMachine.insert(50);
        assertEquals(Arrays.asList(10, 50), vendingMachine.refund());
    }

    @Test
    public void 払い戻し操作を行うと総計は0円になる() throws Exception {
        vendingMachine.insert(10);
        vendingMachine.refund();
        assertEquals(0, vendingMachine.getTotalAmount());
    }

    @Test
    public void 想定内のお金が投入された場合はつり銭として0円を返す() throws Exception {
        assertEquals(0, vendingMachine.insert(10));
    }

    @Test
    public void 扱えないお金を投入した場合は投入した金額をそのまま返す() throws Exception {
        int[] notAllowedMoneys = { 1, 5, 2000, 5000, 10000 };

        for (int i = 0; i < notAllowedMoneys.length; i++) {
            assertEquals(notAllowedMoneys[i],
                    vendingMachine.insert(notAllowedMoneys[i]));
        }
    }

    @Test
    public void 扱えないお金を投入した場合は投入金額の総計は増加しない() throws Exception {
        vendingMachine.insert(1);
        assertEquals(0, vendingMachine.getTotalAmount());
    }
}
